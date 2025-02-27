package com.min01.villageandvillagers.entity;

public interface IAnimatable
{
	boolean isUsingSkill();
	
	void setUsingSkill(boolean value);
	
	void setAnimationTick(int value);
	
	int getAnimationTick();
	
	boolean canMove();
	
	void setCanMove(boolean value);
	
	boolean isCombatMode();
}
