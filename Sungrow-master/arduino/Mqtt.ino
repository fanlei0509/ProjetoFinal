#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <OneWire.h>
#include <DallasTemperature.h>
#include <dht.h>
// ------ Configuracao do Sensor ds108b20 ------

#define ONE_WIRE_BUS 5
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

// --------------------------------------------

// ------ Configuracao do Sensor DHT11 ------

dht DHT;
#define DHT11_PIN 2

// --------------------------------------------

// ------ Configuracao do Wifi ------

const char* ssid = "PcZone";
const char* password = "trezentosetrinta";
const char* mqtt_server = "broker.mqtt-dashboard.com";

//-----------------------------------

//------ Variaveis Declaradas para publicacao------

char temperaturaDagua [50];
char temperaturaAmbiente [50];
char humidade [50];
char luminosidade [50];

// ----------------------------------

//------ Variaveis Declaradas do LDR------

const int sensor_ldr = 0;
int leitura = 0;

// ----------------------------------

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  sensors.begin();  // Inicializa o sensor
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void setup_wifi() {

  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();

}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("brunoMartinelli")) {
      Serial.println("connected");
      // Once connected, publish an announcement...
      client.publish("OnTesteMqttSunGrow", "Ola Mundo");
      Serial.print("Ola Mundo");
      // ... and resubscribe
      client.subscribe("InTesteMqttSunGrow");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}
void loop() {
  

  if (!client.connected()) {
    reconnect();
  }
  client.loop();
  // ------ Publicacao do valor Ds108b20 ------
  
  sensors.requestTemperatures();
  Serial.print("Temperatura Ds108b20 Publicada: ");
  snprintf (temperaturaDagua, 75, "%.2f", sensors.getTempCByIndex(0));
  Serial.println(temperaturaDagua);
  client.publish("pi/sng/tempAg", temperaturaDagua);
  delay (1000);
  
  // -----------------------------------------

  // ------ Publicacao do valor DHT11 ------
  
  int chk = DHT.read11(DHT11_PIN);
  Serial.print("Temperature = ");
  Serial.println(DHT.temperature);
  snprintf (temperaturaAmbiente, 75, "%.2f", DHT.temperature);
  Serial.print("Humidity = ");
  Serial.println(DHT.humidity);
  snprintf (humidade, 75, "%.2f", DHT.humidity);
  client.publish("pi/sng/hmd", humidade);
  client.publish("pi/sng/tempAm", temperaturaAmbiente);
  delay(1000);
  
  // -----------------------------------------

  // ------ Publicacao do valor do LDR ------
  
  //leitura = analogRead(sensor_ldr);
  Serial.print("Intensidade de Luz = ");
  //Serial.println(analogRead(sensor_ldr));
  snprintf (luminosidade, 75, "%d", analogRead(sensor_ldr));
  client.publish("pi/sng/lumin", luminosidade);
  delay(1000);
  
  // -----------------------------------------
  
}
