/*********

   Programme en conception
  Hervé Thomesse
  Complete project details at https://randomnerdtutorials.com/esp8266-dht11dht22-temperature-and-humidity-web-server-with-arduino-ide/
*********/

// Import required libraries
//
#include <Arduino.h>
//#include <ESP8266WiFi.h>
#include <Hash.h>
#include <ESPAsyncTCP.h>
#include <ESPAsyncWebServer.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <time.h>
#include <FS.h>
#include <Wire.h> //*Include the Wire library which allows to use the I2C interface*
#include <Adafruit_Sensor.h>
#include <Adafruit_BME280.h> //*library to easily take readings from the sensor*
#include <ESP8266WiFi.h>           // Use this for WiFi instead of Ethernet.h
#include <MySQL_Connection.h>
#include <MySQL_Cursor.h>
#include "Adafruit_SHT31.h"
#include <NTPClient.h>
#include <WiFiUdp.h>
#include <ArduinoJson.h>  //lirairie pour lire un JSON pour extraire HPA
#include <ESP8266HTTPClient.h>

 #define SEALEVELPRESSURE_HPA (1013.25)


// Parametre server MySQL
IPAddress server_addr(1, 1, 1, 1); // IP of the MySQL *server* here
char user[] = "xxxxxxxxxx";              // MySQL user login username
char passw[] = "xxxxxxxx";        // MySQL user login password
// Sample query
//char INSERT_SQL[] = "INSERT INTO u984373661_prem_db.SensorData (Capteur,Location,Temp,Humid,Press) VALUES ('BME280','Extériur',newT1,newH1)";
WiFiClient client;                 // Use this for WiFi instead of EthernetClient
MySQL_Connection conn(&client);
MySQL_Cursor* cursor;

char* date;

// Replace with your network credentials
const char* ssid = xxxxxxx";
const char* password = "xxxxxxxxx";

// Global variables
byte etatDEL12 = 0;
long dateCourante;
long dateCourante2;
long dateCourante3;
const int ledpin = 12;
unsigned long mtemps = 0; // mémorisation du temps + 500ms
char insert_Sql_1[140];
char insert_Sql_2[140];
char insert_Sql_3[140];
//Week Days
String weekDays[7] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
String months[12] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
char*  meteomaticsLogin =  "trvsynergy_thomesse";
char*  meteomaticsPwd =    "sIwi0Ew2C5";
int bme280_HPA;

boolean s = true; // état de la sortie
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org");

// Assignment of sensors
#define DHTPIN1 0     // Interieur dht22 Digital pin connected to the DHT sensor
#define DHTPIN2 4     // D2 et D1 SCL+SDA BME Digital pin connected to the DHT sensor
#define DHTPIN3 14     // cave DHT 21 Digital pin connected to the DHT sensor

#define DHTTYPE1    DHT11     // DHT 11
#define DHTTYPE2    DHT22     // DHT 22 (AM2302)
#define DHTTYPE    DHT21     // DHT 21 (AM2301)

DHT dht2(DHTPIN1, DHTTYPE1);  // Interieur
DHT dht3(DHTPIN3, DHTTYPE);  // Cave
Adafruit_BME280 bme; // Extérieur
Adafruit_SHT31 sht31 = Adafruit_SHT31();

unsigned long delayTime;

// current temperature & humidity, updated in loop()
float t1 = 0.0; // temperature exterieur
float h1 = 0.0; // humidité extérieur
float p1 = 0.0; // pression atmospherique
float t2 = 0.0; // température intérieur
float h2 = 0.0; // humidité interieur
float t3 = 0.0; // température cave
float h3 = 0.0; // humidité cave

// the IP address for the shield:
IPAddress ip(192, 168, 0, 30);
// Create AsyncWebServer object on port 80
AsyncWebServer server(1030);

// Generally, you should use "unsigned long" for variables that hold time
// The value will quickly become too large for an int to store
unsigned long previousMillis = 0;    // will store last time DHT was updated
unsigned long previousMillis2 = 0;
// Updates DHT readings every 2 seconds
const long interval = 2000;
//-----------------------------------HTML--------------------------------------------------------
const char index_html[] PROGMEM = R"rawliteral(
<!DOCTYPE HTML><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
  <style>
    html {
     font-family: Arial;
     display: inline-block;
     margin: 0px auto;
     text-align: center;
    }
    h2 { font-size: 2rem; }
    p { font-size: 1.5rem; }
    .units { font-size: 1rem; }
    .dht-labels{
      font-size: 1.2rem;
      vertical-align:middle;
      padding-bottom: 5px;
    }
    
  </style>
</head>
<body>
  <h2>Temperature Humidité</h2>
  <span id="date">%Date%</span>

<p>
    <i class="fas fa-thermometer-half" style="color:#059e8a;"></i> 
    <span class="dht-labels">Temperature Exterieur</span> 
    <br>
    <span id="temperature1">%TEMPERATURE1%</span>
    <sup class="units">&deg;C</sup>
  </p>

  <p>
    <i class="fas fa-thermometer-half" style="color:#059e8a;"></i> 
    <span class="dht-labels">Temperature Interieur</span> 
    <br>
    <span id="temperature2">%TEMPERATURE2%</span>
    <sup class="units">&deg;C</sup>
  </p>
  
  <p>
    <i class="fas fa-thermometer-half" style="color:#059e8a;"></i> 
    <span class="dht-labels">Temperature Cave</span> 
    <br>
    <span id="temperature3">%TEMPERATURE3%</span>
    <sup class="units">&deg;C</sup>
  </p>
  <p>
    <i class="fas fa-tint" style="color:#00add6;"></i> 
    <span class="dht-labels">Humidity Exterieur</span>
    <br>
    <span id="humidity1">%HUMIDITY1%</span>
    <sup class="units">%%</sup>
  </p>

   <p>
    <i class="fas fa-tint" style="color:#00add6;"></i> 
    <span class="dht-labels">Humidity Interieur</span>
    <br>
    <span id="humidity2">%HUMIDITY2%</span>
    <sup class="units">%%</sup>
  </p>

   <p>
    <i class="fas fa-tint" style="color:#00add6;"></i> 
    <span class="dht-labels">Humidity Cave</span>
    <br>
    <span id="humidity3">%HUMIDITY3%</span>
    <sup class="units">%</sup>
  </p>

<i class="fa-solid fa-gauge-high"></i>
  
</body>
<script>
setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("date").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/date", true);
  xhttp.send();
}, 1000 ) ;


setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("temperature1").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/temperature1", true);
  xhttp.send();
}, 10000 ) ;

setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("temperature2").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/temperature2", true);
  xhttp.send();
}, 10000 ) ;

setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("temperature3").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/temperature3", true);
  xhttp.send();
}, 10000 ) ;

setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("humidity1").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/humidity1", true);
  xhttp.send();
}, 10000 ) ;

setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("humidity2").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/humidity2", true);
  xhttp.send();
}, 10000 ) ;

setInterval(function ( ) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("humidity3").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "/humidity3", true);
  xhttp.send();
}, 10000 ) ;

</script>
</html>)rawliteral";

//----------------------------Web_SCAPPING_HPA----------------------------------------------
void webScraping() {
  if (WiFi.status() == WL_CONNECTED) {
    std::unique_ptr<BearSSL::WiFiClientSecure> client(new BearSSL::WiFiClientSecure);
    //client->setFingerprint(fingerprint);
    // Or, if you happy to ignore the SSL certificate, then use the following line instead:
    client->setInsecure();
    HTTPClient https;
    Serial.print("[HTTPS] begin...\n");
    if (https.begin(*client, "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=xxxxxxxxxxxx")) {  // HTTPS
      https.setAuthorization(meteomaticsLogin, meteomaticsPwd);
      Serial.print("[HTTPS] GET...\n");
      // start connection and send HTTP header
      int httpCode = https.GET();
      // httpCode will be negative on error
      if (httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        Serial.printf("[HTTPS] GET... code: %d\n", httpCode);
        // file found at server
        if (httpCode == HTTP_CODE_OK || httpCode == HTTP_CODE_MOVED_PERMANENTLY) {
          String payload = https.getString();

          StaticJsonDocument<1024> doc;
          deserializeJson(doc, payload);
          JsonObject main = doc["main"];
          bme280_HPA = main["pressure"]; // 1022
          Serial.println(bme280_HPA);
        }
      }
    } else {
      //  Serial.printf("[HTTPS] GET... failed, error: %s\n", https.errorToString(httpCode).c_str());
    }
    https.end();
  }
  else {
    Serial.printf("[HTTPS] Unable to connect\n");
  }
  Serial.println("Opération effectuée");
}
//--------------------------------------PREPA_SQL--------------------------------------------------

void prepaSQL() {
 
  //BME280
  char var_t1[6];
  dtostrf(t1, 3, 1, var_t1);
  char var_h1[7];
  dtostrf(h1, 3, 1, var_h1);
  char var_p1[7];
  dtostrf(p1, 3, 1, var_p1);
  //DHT11
  char var_t2[6];
  dtostrf(t2, 3, 1, var_t2);
  char var_h2[7];
  dtostrf(h2, 3, 1, var_h2);
  //DHT21
  char var_t3[6];
  dtostrf(t3, 3, 1, var_t3);
  char var_h3[7];
  dtostrf(h3, 3, 1, var_h3);
  Serial.println("");
  Serial.println(var_t2);
  Serial.println(var_h2);
  Serial.println(var_t3);
  Serial.println(var_h3);
  String tab_var[7];
  String tab_var2[] = {var_t1, var_h1, var_p1, var_t2, var_h2, var_t3, var_h3};
  Serial.println(tab_var2[7]);
  Serial.println("");
  for (int i = 0; i < 7; i++) {
    if (tab_var2[i] == "nan") {
      tab_var2[i] = "0";
    }
    tab_var[i] = tab_var2[i];
    Serial.println(tab_var[i]);
  }
  Serial.println(tab_var[7]);

  sprintf(insert_Sql_1, "INSERT INTO u984373661_prem_db.SensorData (Capteur,Location,Temp,Humid,Press) VALUES ('BME280','Extérieur',%s,%s,%s)", tab_var[0], tab_var[1], tab_var[2]);
  sprintf(insert_Sql_2, "INSERT INTO u984373661_prem_db.SensorData (Capteur,Location,Temp,Humid,Press) VALUES ('DHT11','Intérieur',%s,%s,'0')", tab_var[3], tab_var[4]);
  sprintf(insert_Sql_3, "INSERT INTO u984373661_prem_db.SensorData (Capteur,Location,Temp,Humid,Press) VALUES ('SHT31','Cave',%s,%s,'0')", tab_var[5], tab_var[6]);
}

//-----------------------------ENVOI()-------------------------------------------------------------
void envoi() {
  
  webScraping();
  prepaSQL();
  int interv = 0;
  for (int i = 0; i < 3; i++) {
    unsigned long currentMillis2 = millis();
    if (currentMillis2 - previousMillis2 >= interv) {
      // save the last time you updated the DHT values
      previousMillis2 = currentMillis2;
      if (!conn.connected()) {
        if (conn.connect(server_addr, 3306, user, passw)) {
          Serial.println("connection OK.");
        } else {
          Serial.println("connection FAILED.");
          interv = 60000;
          return;
        }
      }
    }
  }
  cursor->execute(insert_Sql_1);
  cursor->execute(insert_Sql_2);
  cursor->execute(insert_Sql_3);
  Serial.println("OK---------------.");
  if (conn.connected()) {
    conn.close();
  }
}
//---------------------------------EMISSION_SI_BESOIN()----------------------------------------------------------
void emissionSiBesoin() {
  const uint32_t periodeEnvoi = 3600; // en secondes
  const uint32_t uneMinute = 60; // en secondes
  static uint32_t dernierEnvoi = -periodeEnvoi;
  timeClient.update();
  uint32_t maintenant = timeClient.getEpochTime(); // nombre de secondes depuis le 1er janvier 1970

  if (((timeClient.getMinutes() == 0) && (timeClient.getSeconds() == 0) && (maintenant - dernierEnvoi > uneMinute)) ||  (maintenant - dernierEnvoi > periodeEnvoi)) {
    envoi();
    dernierEnvoi = maintenant;
  }
}
//--------------------------------SETUP()----------------------------------------------------------
void setup() {
  Serial.begin(115200);
  // Serial port for debugging purposes
  Serial.begin(115200);
  while (!Serial); // wait for serial port to connect. Needed for Leonardo only

  if (! sht31.begin(0x45)) {
    Serial.println("Couldn't find SHT31");
    //while (1) delay(1);
  }

  //dht1.begin();
  dht2.begin();
  dht3.begin();
  bme.begin(0x76);  //*Try to start the device*
  dateCourante2 = millis();
  pinMode(ledpin, OUTPUT);
  // IP statique
  IPAddress dnServer(89, 2, 0, 1);
  IPAddress ip(192, 168, 0, 30);
  IPAddress gateway(192, 168, 0, 1);
  IPAddress subnet(255, 255, 255, 0);

  WiFi.config(ip, dnServer, gateway, subnet);
  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  Serial.println("Connecting to WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println(".");
  }

  Serial.print("Connecting to SQL...  ");
  if (conn.connect(server_addr, 3306, user, passw))
    Serial.println("OK.");
  else
    Serial.println("FAILED.");

  // create MySQL cursor object
  cursor = new MySQL_Cursor(&conn);


  // Print ESP8266 Local IP Address
  Serial.println(WiFi.localIP());

  // Route for root / web page
  server.on("/", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/html", index_html, processor);
  });

  server.on("/date", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(date).c_str());
  });

  server.on("/temperature1", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(t1).c_str());
  });
  server.on("/temperature2", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(t2).c_str());
  });
  server.on("/temperature3", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(t3).c_str());
  });
  server.on("/humidity1", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(h1).c_str());
  });

  server.on("/humidity2", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(h2).c_str());
  });
  server.on("/humidity3", HTTP_GET, [](AsyncWebServerRequest * request) {
    request->send_P(200, "text/plain", String(h3).c_str());
  });

  // Start server
  server.begin();

  configTime(1 * 3600, 0, "0.fr.pool.ntp.org");
  Serial.println("\nWaiting for time");
  while (!time(nullptr)) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("");

  // gestion du temps
  timeClient.begin();
  timeClient.setTimeOffset(7200);

}
//---------------------------------LOOP()---------------------------------------------------------
void loop() {
  time_t now = time(nullptr);
  Serial.println("Final time:");  Serial.println(now);
  Serial.println(ctime(&now));
  //time_t now = time(nullptr);
  //Serial.println(ctime(&now));
  date = ctime(&now);
  //delay(1000);


  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    // save the last time you updated the DHT values
    previousMillis = currentMillis;
    // Read temperature as Celsius (the default)
    float newT1 = bme.readTemperature();
    float newT2 = dht2.readTemperature();
    float newT3 = sht31.readTemperature();//dht3.readTemperature();
    // Read temperature as Fahrenheit (isFahrenheit = true)
    //float newT = dht.readTemperature(true);
    // if temperature read failed, don't change t value
    if (isnan(newT1)) {
      Serial.println("Failed read !");
    }
    else {
      t1 = newT1;
      Serial.println(t1);
      t2 = newT2;
      Serial.println(t2);
      t3 = newT3;
      Serial.println(t3);
    }
    // Read Humidity
    float newH1 = bme.readHumidity();
    float newH2 = dht2.readHumidity();
    float newH3 = sht31.readHumidity();//dht3.readHumidity();
    // if humidity read failed, don't change h value
    if (isnan(newH1)) {
      Serial.println("Failed  read !");
    }
    else {
      h1 = newH1;
      Serial.println(h1);
      h2 = newH2;
      Serial.println(h2);
      h3 = newH3;
      Serial.println(h3);
    }
    // Read Presure
    float newP1 = bme.readPressure() / 100.0F;
    // if humidity read failed, don't change h value
    if (isnan(newP1)) {
      Serial.println("Failed  read !");
    }
    else {
      p1 = newP1;
      Serial.println(p1);
    }
  }

  //----------------------------------Partie en attente Ventilation controlé-------------------------------
  //  if (t1 < 17) {
  //    unsigned long temps = millis(); // lecture du temps système, utiliser une variable comme "temps" permet de l'utiliser pour d'autres action dans le programme
  //
  //
  //    if ( temps >= mtemps )  // si le temps est écoulé
  //    {
  //      digitalWrite( ledpin , s ); // allume la led selon l'état du bit "s"
  //      s = !s;      // inverse l'état de "s"
  //      mtemps = mtemps + 100; // mémorise le temps +100
  //    }
  //  }
  //
  //  else {
  //    unsigned long temps = millis(); // lecture du temps système, utiliser une variable comme "temps" permet de l'utiliser pour d'autres action dans le programme
  //
  //
  //    if ( temps >= mtemps )  // si le temps est écoulé
  //    {
  //      digitalWrite( ledpin , s ); // allume la led selon l'état du bit "s"
  //      s = !s;      // inverse l'état de "s"
  //      mtemps = mtemps + 2000; // mémorise le temps +500
  //    }
  //
  //  }

  //Gestion du temps
  timeClient.update();

  time_t epochTime = timeClient.getEpochTime();

  int currentHour = timeClient.getHours();
  Serial.print("Hour: ");
  Serial.println(currentHour);

  int currentMinute = timeClient.getMinutes();
  Serial.print("Minutes: ");
  Serial.println(currentMinute);

  int currentSecond = timeClient.getSeconds();
  Serial.print("Seconds: ");
  Serial.println(currentSecond);

  String weekDay = weekDays[timeClient.getDay()];
  Serial.print("Week Day: ");
  Serial.println(weekDay);

  //Get a time structure
  struct tm *ptm = gmtime ((time_t *)&epochTime);

  int currentMonth = ptm->tm_mon + 1;
  Serial.print("Month: ");
  Serial.println(currentMonth);

  int currentYear = ptm->tm_year + 1900;
  Serial.print("Year: ");
  Serial.println(currentYear);

  emissionSiBesoin();

  delay(1000);
}
