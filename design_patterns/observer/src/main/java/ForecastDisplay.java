public class ForecastDisplay implements DisplayElement, Observer {
    private final WeatherData weatherData;
    private float temperature;
    private float pressure;
    private float humidity;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.printf("""
                Forecast:
                Temperature: %f
                Pressure: %f
                Humidity: %f
                \n""",
                temperature, pressure, humidity);
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.pressure = weatherData.getPressure();
        this.humidity = weatherData.getHumidity();
        display();
    }
}
