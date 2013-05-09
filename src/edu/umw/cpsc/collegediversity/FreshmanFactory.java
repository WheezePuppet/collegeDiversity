package edu.umw.cpsc.collegediversity;

import java.util.*;
import sim.engine.*;
import sim.field.continuous.*;
import sim.util.*;
import sim.field.network.*;
import ec.util.*;

/**
 * A factory class to instantiate new {@link Student} objects that
 * represent incoming freshmen.
 */
public final class FreshmanFactory implements Steppable {

    private static FreshmanFactory factory;


    private FreshmanFactory(){
    }

    /**
     * Singleton pattern.
     */
    public static synchronized FreshmanFactory instance() {
        if(factory==null){
            factory=new FreshmanFactory();
        }
        return factory;
    }

    /**
     * Instantiate a new crop of {@link Sim.FRESHMAN_CLASS_SIZE} freshmen,
     * and add them to the simulation. This will run every Aug 1st. In 
     * particular:
     * <ul>
     * <li>Instantiate each {@link Student} and give it properties for
     * race, gender, and attributes.</li>
     * <li>Assign it to a freshman orientation group.</li>
     * <li>Schedule it to run in one month (to make connections).</li>
     * <li>Assign it to a dorm (via the {@link FreshmanHousingSelection}).</li>
     * <li>Increment the academic year.</li>
     * </ul>
     */
    public void step(SimState state) {

System.out.println("* FreshmanFactory");
System.out.println("clearing O groups...");
        Sim.instance().clearOGroups();

        Bag freshmen = new Bag();

System.out.println("instantiating freshmen...");
        for(int i = 0; i < Sim.FRESHMAN_CLASS_SIZE; i++){
            Student.Race race;
            Student.Gender gender;
            float racePercent = Sim.instance().random.nextFloat();
            if(racePercent>Sim.PROB_MINORITY){
                race = Student.Race.WHITE;
            }else{
                race = Student.Race.MINORITY;
            }
            double gpa = Sim.instance().random.nextDouble()+(Sim.instance().random.nextInt(3)+1);
            float genderPercent = Sim.instance().random.nextFloat();
            if(genderPercent < .65){
                gender = Student.Gender.FEMALE;
            }
            else{
                gender = Student.Gender.MALE; 
            }
            Student student = new Student(gender,1,gpa,race);

            //give student attributes
            for(int x=0; x<Sim.instance().NUM_PERSONALITY_ATTRIBUTES; x++){
                float attrPercent = state.random.nextFloat();
                if(attrPercent<Sim.PROB_ATTRIBUTE){
                    student.addAttribute(x);
                }
            }

            //assign student to O Group
            Sim.instance().assignOGroup(student);

            freshmen.add(student);
            state.schedule.scheduleOnceIn(1/12.0,student);
        }

        if(Sim.instance().HOUSING_BY_RACE){
            FreshmanHousingSelection.instance().assignByRace(freshmen);
        } else {
System.out.println("assigning freshmen to dorms...");
            FreshmanHousingSelection.instance().assign(freshmen);
        }
System.out.println("adding freshmen to Sim...");
        Sim.instance().addStudents(freshmen);

System.out.println("scheduling FreshmanFactory for next year!");
        state.schedule.scheduleOnceIn(1, this);
    }

}
