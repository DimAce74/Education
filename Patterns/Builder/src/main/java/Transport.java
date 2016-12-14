
public class Transport {
    private String nameOfModel;
    private String nameOfFactory;
    private int yearOfProduction;
    private int enginePower;
    private int yearSales;
    private boolean isRunnable;

    public Transport (Builder builder){
        this.nameOfModel=builder.nameOfModel;
        this.nameOfFactory = builder.nameOfFactory;
        this.yearOfProduction = builder.yearOfProduction;
        this.enginePower = builder.enginePower;
        this.yearSales = builder.yearSales;
        this.isRunnable = builder.isRunnable;
    }

    public static class Builder {
        private String nameOfModel;
        private String nameOfFactory;
        private int yearOfProduction;
        private int enginePower;
        private int yearSales;
        private boolean isRunnable;

        public Builder nameOfModel(String value) {
            this.nameOfModel = value;
            return this;
        }
        public Builder nameOfFactory(String value) {
            this.nameOfFactory = value;
            return this;
        }
        public Builder yearOfProduction(int value) {
            this.yearOfProduction = value;
            return this;
        }
        public Builder enginePower(int value) {
            this.enginePower = value;
            return this;
        }
        public Builder yearSales(int value) {
            this.yearSales = value;
            return this;
        }
        public Builder isRunnable(boolean value) {
            this.isRunnable = value;
            return this;
        }
        public Transport build (){
            return new Transport(this);
        }
    }

    public String getNameOfModel() {
        return nameOfModel;
    }

    public String getNameOfFactory() {
        return nameOfFactory;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public int getYearSales() {
        return yearSales;
    }

    public boolean isRunnable() {
        return isRunnable;
    }


}
