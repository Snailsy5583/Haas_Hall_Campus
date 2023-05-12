import javax.swing.text.AsyncBoxView;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.Collection;

public class Main {
	public static void main(String[] args) {
		School haasHallAcademyBentonville = new School();

		// create 50 rooms
		for (int i = 0; i < 50; i++) {
			Room room = new Room();
			for (int j = 0; j < RandomGenerator.getDefault().nextInt(0, 30); j++) {
				Chair chair = new Chair();
				chair.setlocation(new int[] {
						RandomGenerator.getDefault().nextInt(0, 5),
						RandomGenerator.getDefault().nextInt(0, 5)
				});
				if (RandomGenerator.getDefault().nextBoolean()) {
					Person person = new Person(chair, UUID.randomUUID().toString().replaceAll("-", " "), RandomGenerator.getDefault().nextInt(12, 18), RandomGenerator.getDefault().nextFloat(0, 1));
					chair.setPerson(person);
				} else {
					chair.setPerson(null);
				}

				room.addChair(chair);
			}
			room.id = i;
			room.length = RandomGenerator.getDefault().nextInt(5, 50);
			room.width = RandomGenerator.getDefault().nextInt(5, 50);

			haasHallAcademyBentonville.addRoom(room);
		}

		Room room = haasHallAcademyBentonville.getRoom(RandomGenerator.getDefault().nextInt(0, 50));
		ArrayList<Person> people = room.getPeople();
		if (people.size()>0) {
			Person person = people.get(RandomGenerator.getDefault().nextInt(0, people.size()));
			person.say(person.name + " says hi from room " + room.getId() + " which has " + people.size() + " people!");
		} else {
			System.out.println(room.getId() + " which has an area of " + room.getArea() + " feet is empty.");
		}
	}
}

class School {
	int height;
	ArrayList<Room> rooms;

	public School() {
		this.rooms = new ArrayList<>();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public void removeRoom(Room room) {
		rooms.remove(room);
	}

	public ArrayList<Room> getRoomCollection() {
		return rooms;
	}

	public Room getRoom(int id) {
		for (Room room : rooms) {
			if (room.getId() == id) {
				return room;
			}
		}
		return null;
	}
}

class Room {
	int id;
	int length, width;

	ArrayList<Chair> chairs;

	public Room() {
		this.chairs = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public int getArea() {
		return length*width;
	}
	
	public ArrayList<Chair> getChairs() {
		return chairs;
	}
	public ArrayList<Person> getPeople() {
		ArrayList<Person> people = new ArrayList<>();
		for (Chair chair : chairs) {
			if (chair.getPerson() != null)
				people.add(chair.getPerson());
		}
		return people;
	}

	public Chair getChairAtLocation(int[] pos) {
		for (Chair chair :
				chairs) {
			if (chair.getLocation()[0] == pos[0] &&
				chair.getLocation()[1] == pos[1]) {
				return chair;
			}
		}
		return null;
	}

	public void addChair(Chair chair) {
		chairs.add(chair);
	}
	public void removeChair(Chair chair) {
		chairs.remove(chair);
	}
}

class Chair {
	int x, y;

	Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setlocation(int pos[]) {
		x = pos[0];
		y = pos[1];
	}

	public int[] getLocation() {
		int[] pos = {x,y};
		return pos;
	}
}

class Person {
	Chair chair;
	String name;
	int age;
	float gender; // we're inclusive here

	public Person(Chair chair, String name, int age, float gender) {
		this.chair = chair;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public void say(String phrase) {
		System.out.println(phrase);
	}

	public String getName() {
		return name;
	}

	public void sitDown(Chair chair) {
		this.chair = chair;
	}

	public void getUp() {
		chair = null;
	}
}