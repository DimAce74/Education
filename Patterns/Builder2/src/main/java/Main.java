/**
 * Created by dimac on 14.12.2016.
 */
    public class Main {
        public static void main(String[] args) {
            Transport trans = new Transport.Builder()
                    .enginePower(110)
                    .isRunnable(true)
                    .nameOfFactory("Nissan")
                    .nameOfModel("Note")
                    .build();

            System.out.println(trans.getNameOfModel());
        }
    }


