#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;

uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

uniform vec2 OutSize;
uniform float iTime;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

#define MAX_STEPS 100
#define MAX_DIST 100.
#define SURF_DIST .001
#define TAU 6.283185
#define PI 3.141592
#define S smoothstep
#define T iTime

#define near 0.05
#define far 1000.0
float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

float sdTorus(vec3 p, vec2 t) {
  	vec2 q = vec2(length(p.xz)-t.x,p.y);
  	return length(q)-t.y;
}

float GetDist(vec3 p) {
    float d = sdTorus(p, vec2(fract(iTime * 1.2) * 30, 0.5));
    return d;
}

float RayMarch(vec3 ro, vec3 rd) {
	float dO=0.;
    
    for(int i=0; i<MAX_STEPS; i++) {
    	vec3 p = ro + rd*dO;
        float dS = GetDist(p);
        dO += dS;
        if(dO>MAX_DIST || abs(dS)<SURF_DIST) break;
    }
    
    return dO;
}

void main() {
	vec3 origin = near_4.xyz/near_4.w;  //ray's origin
    vec3 far3 = far_4.xyz/far_4.w;
    vec3 dir = far3 - origin;
    dir = normalize(dir);        //ray's direction
	
	vec3 ro = origin;
	vec3 rd = dir;
    
    vec3 col = texture(DiffuseSampler, texCoord).xyz;
    float depth = linearizeDepth(texture(DepthSampler, texCoord).r);
    
    float d = RayMarch(ro, rd);

    if(d<MAX_DIST && d < depth) {
        vec3 p = ro + rd * d;
        vec2 uv = texCoord;
		float dist = length(uv);
        float wave = sin((dist - T * 0.5) * 20.0) * 0.1 / (dist + 0.1);
		uv += normalize(uv) * wave;
        col = texture(DiffuseSampler, uv).xyz;
    }

	fragColor = vec4(col, 1.0);
}