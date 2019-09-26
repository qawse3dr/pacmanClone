package pacman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Binky extends ghost {
	
	public Binky(int x, int y){
		this.x = x;
		this.y = y+3*16;
		this.colour = "red";
		start = true;
		speed=4;
		Gameobjects.gameObjects.add(this);
		ghost.ghosts[0] = this;
		rect = new Rectangle((int)this.x,(int)this.y,16,16);
	}
	@Override
	public void draw(Graphics g) {
		currentSprite(this);
		g.drawImage(ghost.ghostSpriteSheet, (int)x-5, (int)y-5, (int)x+22, (int)y+22, spriteSheetX*16, spriteSheetY*16, spriteSheetX*16+16, spriteSheetY*16+16, null);
	
	}

	@Override
	public void tick() {
		if (x<-32){
			x=500;
		}else if (x>500){
			x=-32;
		}
		this.rect.x = (int)x;//seting the new rect x and y
		this.rect.y = (int)y;
		if (start){
		//making sides work for the Binky;
		
		if (dead){
			if (new Rectangle(11*16,16*16,6*16,32).contains(rect)){
				dead=false;
				isBlue=false;
			}
		}
		// TODO Auto-generated method stub
		if (dead){
			targetX = 15*16;
			targetY = 272;
		}
		else if (ghost.scatter){
			targetX = 24*16;
			targetY = 0;
		}else{
			targetX = player.currentX;
			targetY= player.currentY;
		}
		
			for (int pointIndex=0; pointIndex<turningPoint.turningPoints.size();pointIndex++){//finds if you can go any other directions
			
			if (rect.contains(turningPoint.turningPoints.get(pointIndex).rect)){
				if (isBlue&& !dead){
					speed=4;
					Random rand = new Random();
					rand.setSeed(System.currentTimeMillis());
				movement tempDirection;
				while (true){	
					if (turningPoint.turningPoints.get(pointIndex).availableMoves.length ==1){
						direction = turningPoint.turningPoints.get(pointIndex).availableMoves[0];
						break;
					}
					 tempDirection =
							turningPoint.turningPoints.get(pointIndex).availableMoves[
							  rand.nextInt(turningPoint.turningPoints.get(pointIndex).availableMoves.length)];
					if (tempDirection == movement.left&&direction == movement.right){
						continue;
					}else if (tempDirection == movement.right&&direction == movement.left){
						continue;
					}else if (tempDirection == movement.up&&direction == movement.down){
					continue;
					}else if (tempDirection == movement.down&&direction == movement.up){
						continue;}
					else{direction = tempDirection;
							break;}
					}
				}else{
				movement bestMove=movement.none;
				int bestMoveDistance=0;
				int tempDis;
				for (movement move: turningPoint.turningPoints.get(pointIndex).availableMoves){
					if (isBlue && !dead){
						Random rand = new Random();
						rand.setSeed(System.currentTimeMillis());
						
					//while (true){	
						 direction =
								turningPoint.turningPoints.get(pointIndex).availableMoves[
								  rand.nextInt(turningPoint.turningPoints.get(pointIndex).availableMoves.length-1)];
						 movement tempDirection;
							while (true){	
								if (turningPoint.turningPoints.get(pointIndex).availableMoves.length ==1){
									direction = turningPoint.turningPoints.get(pointIndex).availableMoves[0];
									break;
								}
								 tempDirection =
										turningPoint.turningPoints.get(pointIndex).availableMoves[
										  rand.nextInt(turningPoint.turningPoints.get(pointIndex).availableMoves.length)];
								if (tempDirection == movement.left&&direction == movement.right){
									continue;
								}else if (tempDirection == movement.right&&direction == movement.left){
									continue;
								}else if (tempDirection == movement.up&&direction == movement.down){
								continue;
								}else if (tempDirection == movement.down&&direction == movement.up){
									continue;}
								else{direction = tempDirection;
										break;}
								}
					}else
					if (dead){
						
						if (rect.contains(new Rectangle(13*16,224,16,16))){
						
							bestMove = movement.down;
							break;
						}
					}if (turnAround){
						if(move == movement.left && direction == movement.right){
							bestMove = movement.left;
							turnAround=false;
							break;
						}else if(move == movement.right && direction == movement.left){
							bestMove = movement.right;
							turnAround=false;
							break;
					}else if(move == movement.up && direction == movement.down){
						bestMove = movement.up;
						turnAround=false;
						break;
				}else if(move == movement.down && direction == movement.up){
					bestMove = movement.down;
					turnAround=false;
					break;
			}
					}
					if (move == movement.left && direction != movement.right){
						tempDis= (int)Math.sqrt(Math.pow((x-16-targetX),2)+Math.pow((y-targetY), 2));
						
						if (tempDis <bestMoveDistance || bestMoveDistance == 0){
							bestMoveDistance=tempDis;
							bestMove = movement.left;
						}
					}else if (move == movement.right && direction != movement.left){
						tempDis= (int)Math.sqrt(Math.pow((x+16-targetX),2)+Math.pow((y-targetY), 2));
					
						if (tempDis <bestMoveDistance || bestMoveDistance == 0){
							bestMove = movement.right;
							bestMoveDistance=tempDis;
					}
					}else if (move == movement.up && direction != movement.down){
						tempDis= (int)Math.sqrt(Math.pow((x-targetX),2)+Math.pow((y-16-targetY), 2));
					
						if (tempDis <bestMoveDistance || bestMoveDistance == 0){
							bestMove = movement.up;
							bestMoveDistance=tempDis;}
					}else if (move == movement.down&& direction != movement.up){
						tempDis= (int)Math.sqrt(Math.pow((x-targetX),2)+Math.pow((y+16-targetY), 2));
						
						if (tempDis <bestMoveDistance || bestMoveDistance == 0){
							bestMove = movement.down;
							bestMoveDistance=tempDis;
					}}
					}
				
				direction = bestMove;
			}}
			
			
			}
			if (direction == movement.left){
				x-=speed;
			}else if (direction == movement.right){
				x+=speed;
			}else if (direction == movement.up){
				y-=speed;
			}else if (direction == movement.down){
				y+=speed;
			}
			}
	}
	}
	


