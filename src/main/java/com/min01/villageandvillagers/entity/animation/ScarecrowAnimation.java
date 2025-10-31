package com.min01.villageandvillagers.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ScarecrowAnimation 
{
	public static final AnimationDefinition SCARECROW_HURT = AnimationDefinition.Builder.withLength(0.5F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(4.9811F, 0.4352F, -4.9811F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(5.0F, -5.0F, 2.12F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(7.5F, 2.5F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("upperbody", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.125F, KeyframeAnimations.degreeVec(-19.9299F, 1.7082F, 9.6999F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2917F, KeyframeAnimations.degreeVec(12.3964F, 1.6189F, -7.3242F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition SCARECROW_IDLE = AnimationDefinition.Builder.withLength(3.5833F).looping()
			.addAnimation("upperbody", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(1.0F, -0.87F, -0.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0417F, KeyframeAnimations.degreeVec(1.0F, -0.83F, -0.44F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.99F, -0.78F, -0.37F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.98F, -0.74F, -0.3F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.96F, -0.69F, -0.23F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.93F, -0.63F, -0.16F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.91F, -0.57F, -0.09F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.87F, -0.51F, -0.01F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.84F, -0.45F, 0.06F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.79F, -0.38F, 0.13F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.75F, -0.31F, 0.2F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.7F, -0.24F, 0.27F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.64F, -0.17F, 0.34F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.59F, -0.1F, 0.41F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.52F, -0.03F, 0.47F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.46F, 0.04F, 0.54F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.4F, 0.12F, 0.6F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7083F, KeyframeAnimations.degreeVec(0.33F, 0.19F, 0.65F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.26F, 0.26F, 0.71F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7917F, KeyframeAnimations.degreeVec(0.19F, 0.33F, 0.76F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.12F, 0.4F, 0.8F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.04F, 0.46F, 0.84F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9167F, KeyframeAnimations.degreeVec(-0.03F, 0.52F, 0.88F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9583F, KeyframeAnimations.degreeVec(-0.1F, 0.59F, 0.91F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0F, KeyframeAnimations.degreeVec(-0.17F, 0.64F, 0.94F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0417F, KeyframeAnimations.degreeVec(-0.24F, 0.7F, 0.96F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0833F, KeyframeAnimations.degreeVec(-0.31F, 0.75F, 0.98F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.125F, KeyframeAnimations.degreeVec(-0.38F, 0.79F, 0.99F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-0.45F, 0.84F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2083F, KeyframeAnimations.degreeVec(-0.51F, 0.87F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.25F, KeyframeAnimations.degreeVec(-0.57F, 0.91F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2917F, KeyframeAnimations.degreeVec(-0.63F, 0.93F, 0.99F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-0.69F, 0.96F, 0.97F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.375F, KeyframeAnimations.degreeVec(-0.74F, 0.98F, 0.95F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.4167F, KeyframeAnimations.degreeVec(-0.78F, 0.99F, 0.93F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.4583F, KeyframeAnimations.degreeVec(-0.83F, 1.0F, 0.9F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5F, KeyframeAnimations.degreeVec(-0.87F, 1.0F, 0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-0.9F, 1.0F, 0.83F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.5833F, KeyframeAnimations.degreeVec(-0.93F, 0.99F, 0.78F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.625F, KeyframeAnimations.degreeVec(-0.95F, 0.98F, 0.74F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-0.97F, 0.96F, 0.69F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.7083F, KeyframeAnimations.degreeVec(-0.99F, 0.93F, 0.63F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.75F, KeyframeAnimations.degreeVec(-1.0F, 0.91F, 0.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.7917F, KeyframeAnimations.degreeVec(-1.0F, 0.87F, 0.51F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.8333F, KeyframeAnimations.degreeVec(-1.0F, 0.84F, 0.45F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.875F, KeyframeAnimations.degreeVec(-0.99F, 0.79F, 0.38F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.9167F, KeyframeAnimations.degreeVec(-0.98F, 0.75F, 0.31F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.9583F, KeyframeAnimations.degreeVec(-0.96F, 0.7F, 0.24F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0F, KeyframeAnimations.degreeVec(-0.94F, 0.64F, 0.17F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0417F, KeyframeAnimations.degreeVec(-0.91F, 0.59F, 0.1F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-0.88F, 0.52F, 0.03F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.125F, KeyframeAnimations.degreeVec(-0.84F, 0.46F, -0.04F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.1667F, KeyframeAnimations.degreeVec(-0.8F, 0.4F, -0.12F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.2083F, KeyframeAnimations.degreeVec(-0.76F, 0.33F, -0.19F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.25F, KeyframeAnimations.degreeVec(-0.71F, 0.26F, -0.26F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.2917F, KeyframeAnimations.degreeVec(-0.65F, 0.19F, -0.33F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-0.6F, 0.12F, -0.4F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.375F, KeyframeAnimations.degreeVec(-0.54F, 0.04F, -0.46F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.4167F, KeyframeAnimations.degreeVec(-0.47F, -0.03F, -0.52F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.4583F, KeyframeAnimations.degreeVec(-0.41F, -0.1F, -0.59F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5F, KeyframeAnimations.degreeVec(-0.34F, -0.17F, -0.64F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5417F, KeyframeAnimations.degreeVec(-0.27F, -0.24F, -0.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.5833F, KeyframeAnimations.degreeVec(-0.2F, -0.31F, -0.75F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.625F, KeyframeAnimations.degreeVec(-0.13F, -0.38F, -0.79F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-0.06F, -0.45F, -0.84F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.7083F, KeyframeAnimations.degreeVec(0.01F, -0.51F, -0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.09F, -0.57F, -0.91F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.7917F, KeyframeAnimations.degreeVec(0.16F, -0.63F, -0.93F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.23F, -0.69F, -0.96F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.875F, KeyframeAnimations.degreeVec(0.3F, -0.74F, -0.98F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.9167F, KeyframeAnimations.degreeVec(0.37F, -0.78F, -0.99F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(2.9583F, KeyframeAnimations.degreeVec(0.44F, -0.83F, -1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.5F, -0.87F, -1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0417F, KeyframeAnimations.degreeVec(0.56F, -0.9F, -1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.0833F, KeyframeAnimations.degreeVec(0.62F, -0.93F, -0.99F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.125F, KeyframeAnimations.degreeVec(0.68F, -0.95F, -0.98F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.1667F, KeyframeAnimations.degreeVec(0.73F, -0.97F, -0.96F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.2083F, KeyframeAnimations.degreeVec(0.78F, -0.99F, -0.93F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.25F, KeyframeAnimations.degreeVec(0.82F, -1.0F, -0.91F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.2917F, KeyframeAnimations.degreeVec(0.86F, -1.0F, -0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.89F, -1.0F, -0.84F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.375F, KeyframeAnimations.degreeVec(0.92F, -0.99F, -0.79F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.4167F, KeyframeAnimations.degreeVec(0.95F, -0.98F, -0.75F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.4583F, KeyframeAnimations.degreeVec(0.97F, -0.96F, -0.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.98F, -0.94F, -0.64F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5417F, KeyframeAnimations.degreeVec(0.99F, -0.91F, -0.59F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(3.5833F, KeyframeAnimations.degreeVec(1.0F, -0.88F, -0.52F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("body2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}
