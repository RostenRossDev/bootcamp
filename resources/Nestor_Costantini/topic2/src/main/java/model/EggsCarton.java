package model;

import org.apache.log4j.Logger;

import com.globant.bootcamp.topic2.constants.NumberConstants;
import com.globant.bootcamp.topic2.enums.Bird;
import com.globant.bootcamp.topic2.enums.Color;
import model.FactoryPattern.EggFactory;
import model.animal.Egg;

public class EggsCarton {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	
    private Egg[][] eggs = new Egg[5][6];
    
    private int eggCount;
    
    private boolean full;
    
    private Color color;
    
    private int [] freePlace = new int[2];

    public EggsCarton( Color color, EggFactory eggFactory ){

        this.eggCount=0;

        this.freePlace[0]=0;

        this.freePlace[1]=0;

        this.full=false;

        this.color=color;

        this.fillEmptyEggs( eggFactory );

    }

    public Egg[][] getEggs(){

        return  this.eggs;
    }

    public boolean isFull(){

        return this.full;
    }

    public void addEgg( Egg egg ){
        if( this.color.equals( egg.getColor() ) ){

            this.eggs[this.freePlace[0]][this.freePlace[1]] = egg;

            eggCount++;

            this.updateFreePlace();

            fullControl();
        }
    }

    private void updateFreePlace(){

        if( this.freePlace[0]<5 ){
        	
            this.freePlace[0]++;

            if ( this.freePlace[0]>4 ){
            	
                this.freePlace[0]=0;

                this.freePlace[1]++;
            }
        }
    }

    private void fullControl(){

        if (NumberConstants.MAX_EGG == this.eggCount ){
            this.full=true;
            LOG.warn("Egg Carton is full!!!");
        }
    }

    private void fillEmptyEggs(EggFactory eggFactory){
        for (int i = 0; i <6 ; i++) {

            for (int j = 0; j <5 ; j++) {
                this.eggs[j][i]=(Egg) eggFactory.getAnimal(null, Bird.Hen);
            }
        }
    }
}
