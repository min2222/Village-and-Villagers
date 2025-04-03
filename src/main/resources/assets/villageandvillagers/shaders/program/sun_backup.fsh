#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;
uniform sampler2D FireNoise;  // Fire/ember texture
uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;
uniform vec2 OutSize;
uniform float Time;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

#define near 0.05
#define far 1000.0
#define PI 3.14159265359

// Hellgate parameters
uniform vec3 GatePosition = vec3(0.0, 0.0, 5.0);
uniform float GateRadius = 1.5;
uniform float FlameSpeed = 2.0;
uniform float LavaFlowSpeed = 0.5;

float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

vec3 hellCoreEffect(vec3 p, vec3 center) {
    // Lava flow pattern
    float lava = sin(p.x * 4.0 + Time * LavaFlowSpeed) * 
                cos(p.y * 3.0 + Time * LavaFlowSpeed) * 
                sin(p.z * 2.0 + Time * LavaFlowSpeed);
    
    // Flame intensity
    float flame = texture(FireNoise, vec2(Time * FlameSpeed)).r;
    
    // Core colors
    vec3 lavaColor = mix(vec3(1.0, 0.3, 0.0), vec3(0.8, 0.1, 0.0), lava);
    vec3 flameColor = mix(vec3(1.0, 0.5, 0.0), vec3(0.9, 0.1, 0.0), flame);
    
    return (lavaColor * 0.7 + flameColor * 0.3) * smoothstep(GateRadius, 0.0, length(p - center));
}

vec2 infernalDistortion(vec2 uv, vec3 p, vec3 center) {
    vec2 offset = vec2(0.0);
    float dist = length(p - center);
    
    // Heat wave effect
    offset.x = sin(Time * 4.0 + uv.y * 10.0) * 0.02 * (1.0 - smoothstep(0.0, GateRadius * 2.0, dist));
    offset.y = cos(Time * 3.5 + uv.x * 8.0) * 0.015 * (1.0 - smoothstep(0.0, GateRadius * 2.0, dist));
    
    return offset;
}

void main() {
    vec3 origin = near_4.xyz/near_4.w;
    vec3 far3 = far_4.xyz/far_4.w;
    vec3 dir = normalize(far3 - origin);
    
    vec3 gateVec = GatePosition - origin;
    float t = dot(gateVec, dir);
    vec3 closest = origin + dir * t;
    float dist = length(closest - GatePosition);
    
    // Apply heat distortion
    vec2 distortedUV = texCoord + infernalDistortion(texCoord, origin + dir * t, GatePosition);
    vec3 col = texture(DiffuseSampler, distortedUV).xyz;
    
    if(dist < GateRadius * 1.5) {
        // Hellgate core effects
        vec3 core = hellCoreEffect(origin + dir * t, GatePosition);
        
        // Ember particles
        float ember = texture(FireNoise, vec2(Time * 5.0)).g;
        vec3 emberColor = vec3(1.0, 0.4, 0.1) * ember * smoothstep(GateRadius * 1.2, GateRadius * 0.8, dist);
        
        // Smoke effect
        float smoke = texture(FireNoise, vec2(Time * 0.5)).b;
        col = mix(col, vec3(0.2), smoke * 0.3 * (1.0 - smoothstep(0.0, GateRadius * 1.5, dist)));
        
        // Combine effects
        col = mix(col, core, smoothstep(GateRadius * 1.2, GateRadius * 0.8, dist));
        col += emberColor * 2.0;
        
        // Burning edges
        float edge = smoothstep(GateRadius * 0.9, GateRadius * 0.8, dist);
        col += edge * vec3(1.0, 0.3, 0.0) * 0.6;
    }

    fragColor = vec4(col, 1.0);
}
