package model.animal;

import com.globant.bootcamp.topic2.constants.StringConstans;
import com.globant.bootcamp.topic2.enums.Color;
import model.FactoryPattern.AnimalFactory;

public class Egg extends Animal {

    private Color color;

    private Bird baby;

    public Egg( Bird bird, Color color ){

        super( randomGender() );

        this.baby=bird;

        this.color=color;
    }

    public Egg(){

        super(null );
    }

    public Color getColor(){

        return this.color;
    }

    public Animal<Bird> getBaby() {
    	return (Animal) this.baby;
    }
    
    @Override
    public String toString() {

        String eggStr=StringConstans.EMPTY_EGG;

        if( Color.RED.equals(this.color) ){

            eggStr=StringConstans.RED_EGG;
        }else if( Color.WHITE.equals(this.color) ){

            eggStr=StringConstans.WHITE_EGG;
        }

        return eggStr;
    }

    @Override
    public void makeSound() {

        System.out.println(StringConstans.EGG_SOUND);
    }

    @Override
    public Object gaveBirth(Color color, AnimalFactory animalFactory) {

        return null;
    }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Egg) {
			Egg egg = (Egg) obj;
			if(this.getColor().equals(egg.getColor()) && this.getBaby().equals(egg.getBaby())) {
				return true;
			}
		}
			
		return false;
	}

    
}
