var tempExt1 = [];
var tempInt1 = [];
var tempCave1 = [];
var humidExt1 = [];
var humidInt1 = [];
var humidCave1 = [];
var pressExt1 = [];
var dateServer1 = [];
day = "";

//Changement du datePicker et actualisation de la page
document.addEventListener("DOMContentLoaded", function () {
  var dateInput = document.querySelector('[name="oneDate"]');
  dateInput.addEventListener("input", function (event) {
    day = $("#getPickerDate").val();
    localStorage.setItem("getDay", day);
    window.location.reload(true);
  });
  //Récupération de la variable localStorage
  if (localStorage.getItem("getDay")) {
    day = localStorage.getItem("getDay");
    $("#getPickerDate").val(day);
  }

  const btLast24 = document.querySelector("#btLast24");
  btLast24.addEventListener("click", (event) => {
    localStorage.removeItem("getDay");
    $("#getPickerDate").datepicker("setDate", null);
    window.location.reload(true);
    window.location.reload(true);
  });

  //Import des données et affichage du Graphe Chart.js
  json24();

  async function json24() {
    day = $("#getPickerDate").val();
    if (day == "") {
      day = "last24";
    }
    console.log(day);

    urlGet = "http://localhost:8080/api/" + day;
    console.log(urlGet);
    const response = await fetch(urlGet);

    data = await response.json();

    var i = 0;
    for (let r of data) {
      tempExt1[i] = r.tempExt;
      tempInt1[i] = r.tempInt;
      tempCave1[i] = r.tempCave;
      humidExt1[i] = r.humidExt;
      humidInt1[i] = r.humidInt;
      humidCave1[i] = r.humidCave;
      pressExt1[i] = r.pressExt;
      dateServer1[i] = r.dateServer;
      i += 1;
    }
    var heure = [];
    for (let i of dateServer1) {
      var h = parseInt(i.slice(11, 13));
      h = h + 1;

      heure.push(h + "h");
    }

    console.log(humidExt1);
    console.log(humidInt1);
    console.log(humidCave1);
    console.log(pressExt1);
    console.log(heure);
    if (document.getElementById("myChartTemp") != null) {
      var ctx1 = document.getElementById("myChartTemp").getContext("2d");
      new Chart(ctx1, {
        type: "line",
        data: {
          labels: heure.reverse(),
          datasets: [
            {
              label: "Tempetrature extérieur",
              data: tempExt1.reverse(),
              borderWidth: 1,
            },
            {
              label: "Tempetrature interieur",
              data: tempInt1.reverse(),
              borderWidth: 1,
            },
            {
              label: "Tempetrature cave",
              data: tempCave1.reverse(),
              borderWidth: 1,
            },
          ],
        },
        options: {
          padding: { top: 100 },
          responsive: true,
          scales: {
            y: {
              suggestedMin: 20,
              beginAtZero: true,
            },
          },
        },
      });
    }

    if (document.getElementById("myChartHumid") != null) {
      var ctx2 = document.getElementById("myChartHumid").getContext("2d");
      new Chart(ctx2, {
        type: "line",
        data: {
          labels: heure.reverse(),
          datasets: [
            {
              label: "Humidité extérieur",
              data: humidExt1.reverse(),
              borderWidth: 1,
            },
            {
              label: "Humidité interieur",
              data: humidInt1.reverse(),
              borderWidth: 1,
            },
            {
              label: "Humidité cave",
              data: humidCave1.reverse(),
              borderWidth: 1,
            },
          ],
        },
        options: {
          padding: 100,
          responsive: true,
          scales: {
            y: {
              suggestedMin: 50,
              suggestedMax: 100,
            },
          },
        },
      });
    }

    if (document.getElementById("myChartPress") != null) {
      var ctx3 = document.getElementById("myChartPress").getContext("2d");
      new Chart(ctx3, {
        type: "line",
        data: {
          labels: heure.reverse(),
          datasets: [
            {
              label: "Pression atmosphérique",
              data: pressExt1.reverse(),
              borderWidth: 1,
            },
          ],
        },
        options: {
          padding: 100,
          responsive: true,
          scales: {
            y: {
              suggestedMin: 990,
              uggestedMax: 1050,
            },
          },
        },
      });
    }
  }
});

//Actualisation de la page toutes les heures et 1 minute
strict();
function strict() {
  // Syntaxe en mode strict au niveau de la fonction
  "use strict";
  document.addEventListener("DOMContentLoaded", (ev) => {}, false);

  window.addEventListener(
    "load",
    (ev) => {
      window.setInterval(() => {
        const now = new Date();

        if (now.getMinutes() == 1 && now.getSeconds() >= 0) {
          window.location.reload(true);
          console.log("reloaded");
        }
      }, 40000); // 40s
    },
    false
  );
}
