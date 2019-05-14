import java.util.List;
import java.util.Random;

public class RandomMovment {
	
	public static Coordinates getRandomEntityMovment(Coordinates locationXY) {
		
		int tempX=0, tempY=0;
		int ranNum;
		
		Random rn = new Random();
				
		tempY = Integer.parseInt(locationXY.getYLocation());
		tempX = Integer.parseInt(locationXY.getXLocation());
			do {
				tempX = (tempX > 100 ? tempX - 5 : tempX);
				tempX = (tempX < 0 ? tempX + 5 : tempX);
				tempY = (tempY > 100 ? tempY - 5 : tempY);
				tempY = (tempY < 0 ? tempX + 5 : tempY);
				
				ranNum = rn.nextInt(4);
				DirectionEnum directionEnumType = DirectionEnum.values()[ranNum];
				switch (directionEnumType) {
				case UP:
					tempY = tempY - 5;
					break;
				case DOWN:
					tempY = tempY + 5;
					break;
				case LEFT:
					tempX = tempX - 5;
					break;
				case RIGHT:
					tempX = tempX + 5;
					break;
				default:
					System.out.println("out of range");
					break;
				}
			} while (tempX > 100 || tempX < 0 || tempY > 100 || tempY < 0);
		
		locationXY.setXLocation(String.valueOf(tempX));
		locationXY.setYLocation(String.valueOf(tempY));
		
		return locationXY;
	}
	
	public static boolean moveEntitysLocation() {
		String xLocation, yLocation;
		int numOfVisibleEntities = 0;
		int numOfBlockedEntities = 0;
		
		for(int i=0 ; i < EntityData.listEntityData.size() && i < EntityData.MAX_ENTITY; i++) {
			if (EntityData.listEntityData.get(i).getIsBlocked()) {
				numOfBlockedEntities++;
				continue;
			}
			if (EntityData.listEntityData.get(i).getIsVisible()) {
				numOfVisibleEntities++;
				xLocation = EntityData.listEntityData.get(i).getXLocation();
				yLocation = EntityData.listEntityData.get(i).getYLocation();
				Coordinates locationXY = new Coordinates(xLocation, yLocation);
				
				List<String> dimension = EntityData.listEntityData.get(i).getDimension();
				String newLocation = printNewLocationXY(xLocation, yLocation, i); 

				while (dimension.contains(newLocation)){
					locationXY = getRandomEntityMovment(locationXY);

					newLocation = printNewLocationXY(locationXY.getXLocation(), locationXY.getYLocation(), i); 
					if (dimension.contains(newLocation)){
						System.out.println("Entity elD"+ EntityData.listEntityData.get(i).getEntityID() 
								+" already passed location : "
								+  locationXY.getXLocation() + "," + locationXY.getYLocation()+ 
								". The system will try choosing new location" );
						locationXY.setXLocation(EntityData.listEntityData.get(i).getXLocation());
						locationXY.setYLocation(EntityData.listEntityData.get(i).getYLocation());
						// check all 4 location around the current location
						EntityData.listEntityData.get(i).setIsBlocked(foundNewLocation(dimension, locationXY.getXLocation(), locationXY.getYLocation()));
						if (EntityData.listEntityData.get(i).getIsBlocked()) {
							System.out.println("Entity elD" + EntityData.listEntityData.get(i).getEntityID() + " is Blocked !!!!!!!!!");
							break;
						}
					}
				}
			
				EntityData.listEntityData.get(i).setXLocation(locationXY.getXLocation());
				EntityData.listEntityData.get(i).setYLocation(locationXY.getYLocation());
				EntityData.listEntityData.get(i).setLocationXY(locationXY);

				dimension.add(locationXY.getXLocation() + "," + locationXY.getYLocation());
			}
		}
		
		return (numOfBlockedEntities >= numOfVisibleEntities);
	}
	
	public static String printNewLocationXY(String xLocation, String yLocation, int i) {
		String newLocation = xLocation + "," + yLocation; 
		System.out.println("Current elD" + EntityData.listEntityData.get(i).getEntityID() + 
				" location: " + EntityData.listEntityData.get(i).getXLocation() + "," + EntityData.listEntityData.get(i).getYLocation());
		return newLocation;
	}
	
	// check if all 4 location around the current location has been visited by the current entity
	public static boolean foundNewLocation(List<String> dimension, String xLocation, String yLocation) {
		final int DISTANCE = 5;
		int currentX = Integer.parseInt(xLocation), tmpX;
		int currentY = Integer.parseInt(yLocation), tmpY;
		int counter = 0; 
	
		tmpX = currentX + DISTANCE;
		String tmpXLocation = String.valueOf(tmpX);
		String newLocation = tmpXLocation + "," + yLocation; 
		if (tmpX > 100 || dimension.contains(newLocation)) counter++;
		
		tmpX = currentX - DISTANCE;
		tmpXLocation = String.valueOf(tmpX);
		newLocation = tmpXLocation + "," + yLocation; 
		if (tmpX < 0 || dimension.contains(newLocation)) counter++;
		
		tmpY = currentY + DISTANCE;
		String tmpYLocation = String.valueOf(tmpY);
		newLocation = xLocation + "," + tmpYLocation; 
		if (tmpY > 100 || dimension.contains(newLocation)) counter++;
		
		tmpY = currentY - DISTANCE;
		tmpYLocation = String.valueOf(tmpY);
		newLocation = xLocation + "," + tmpYLocation; 
		if (tmpY < 0 || dimension.contains(newLocation)) counter++;
	
		return (counter == 4);
	}

		

}
