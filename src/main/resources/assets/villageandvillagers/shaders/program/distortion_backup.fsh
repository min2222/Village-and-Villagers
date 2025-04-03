#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;

uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

uniform vec2 OutSize;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

const float inf = uintBitsToFloat(0x7F800000u);

#define near 0.05
#define far 1000.0
#define MAX_STEPS 100
#define MAX_DIST 100.0
#define EPSILON 0.001

float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

vec3 applyLensing(vec3 ro, vec3 rd, vec2 uv) {
    float lensingSphereRadius = 7.0;
    vec3 blackHoleCenter = vec3(0.0); // Assuming black hole at world origin

    vec3 oc = ro - blackHoleCenter;
    float a = dot(rd, rd);
    float b = 2.0 * dot(oc, rd);
    float c = dot(oc, oc) - lensingSphereRadius * lensingSphereRadius;
    float discriminant = b * b - 4.0 * a * c;

    if (discriminant > 0.0) {
        float t = (-b - sqrt(discriminant)) / (2.0 * a);
        if (t > 0.0) {
            vec3 intersectionPoint = ro + t * rd;
            vec3 lensToIntersection = intersectionPoint - blackHoleCenter;
            vec3 lensToIntersectionNormalized = normalize(lensToIntersection);

            // Calculate screen-space center of the black hole
            vec4 worldPos = vec4(blackHoleCenter, 1.0);
            vec4 screenPos = ProjectionMatrix * ViewMatrix * worldPos;
            vec2 screenCenter = (screenPos.xy / screenPos.w) * 0.5 + 0.5;

            vec2 dir = 0.2 * normalize(uv - screenCenter);
            float strength = dot(-rd, lensToIntersectionNormalized) * 1.5;
            vec2 distortedUV = uv - dir * strength;
            distortedUV.y = clamp(distortedUV.y, 2 / OutSize.y, 1);

            // Sample the texture with the distorted UV and return the color
            return texture(DiffuseSampler, distortedUV).rgb;
        }
    }
    return texture(DiffuseSampler, uv).rgb;
}

void main() {
    vec3 ro = near_4.xyz / near_4.w;
    vec3 far3 = far_4.xyz / far_4.w;
    vec3 rd = normalize(far3 - ro);

    vec2 uv = texCoord;
    uv.y = clamp(uv.y, 2 / OutSize.y, 1);

    // Apply lensing to get the background color
    vec3 backgroundColor = applyLensing(ro, rd, uv);

    fragColor = vec4(backgroundColor, 1.0);
}