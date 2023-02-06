var tempExt1 = [];
var tempInt1 = [];
var tempCave1 = [];
var humidExt1 = [];
var humidInt1 = [];
var humidCave1 = [];
var pressExt1 = [];
var dateServer1 = [];
day = "";
tabTHP = "";
nameInput = "";
reverse = 0;
// function de confirmation de quitter le site pour un autre
function ConfirmMessage() {
  if (
    confirm(
      "Vous allez être rediriger sur un autre site dans un nouvel onglet."
    )
  ) {
    return true;
  } else {
    return false;
  }
}
// incrementa
function increment() {
  day = $("#getPickerDate").val();
  day1 = new Date(day);
  tomorrow = new Date(day1.getFullYear(), day1.getMonth(), day1.getDate() + 1);
  tomorrow = tomorrow.toLocaleDateString("en-CA");
  $("#getPickerDate").val(tomorrow);
  localStorage.removeItem("getDay");
  localStorage.setItem("getDay", tomorrow);
  window.location.reload(true);
}
function decrement() {
  day = $("#getPickerDate").val();
  day1 = new Date(day);
  yesterday = new Date(day1.getFullYear(), day1.getMonth(), day1.getDate() - 1);
  yesterday = yesterday.toLocaleDateString("en-CA");
  $("#getPickerDate").val(yesterday);
  localStorage.removeItem("getDay");
  localStorage.setItem("getDay", yesterday);
  window.location.reload(true);
}
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
  });

  //Import des données et affichage du Graphe Chart.js

  async function json24() {
    day = $("#getPickerDate").val();
    if (day == "") {
      day = "last24";
      reverse = 1;
    } else {
      reverse = 0;
    }
    console.log(day);

    urlGet = "http://85.168.31.223:8080/api/" + day;
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

    if (reverse == 1) {
      tempExt1 = tempExt1.reverse();
      tempInt1 = tempInt1.reverse();
      tempCave1 = tempCave1.reverse();
      humidExt1 = humidExt1.reverse();
      humidInt1 = humidInt1.reverse();
      humidCave1 = humidCave1.reverse();
      pressExt1 = pressExt1.reverse();
      heure = heure.reverse();
    }

    function thpmax(tabTHP, nameInput, idHeure) {
      var elt = document.querySelector("input[name=" + nameInput + "]");
      elt.value = Math.max(...tabTHP);
      numIndex = tabTHP.indexOf(Math.max(...tabTHP));
      document.getElementById(idHeure).innerHTML = heure[numIndex];
    }
    function thpmin(tabTHP, nameInput, idHeure) {
      var elt = document.querySelector("input[name=" + nameInput + "]");
      elt.value = Math.min(...tabTHP);
      numIndex = tabTHP.indexOf(Math.min(...tabTHP));
      document.getElementById(idHeure).innerHTML = heure[numIndex];
    }
    if (document.getElementById("myChartTemp") != null) {
      thpmax(tempExt1, "maxtempExt", "maxHeurExt");
      thpmin(tempExt1, "mintempExt", "minHeurExt");
      thpmax(tempInt1, "maxtempInt", "maxHeurInt");
      thpmin(tempInt1, "mintempInt", "minHeurInt");
      thpmax(tempCave1, "maxtempCave", "maxHeurCave");
      thpmin(tempCave1, "mintempCave", "minHeurCave");
    }
    if (document.getElementById("myChartHumid") != null) {
      thpmax(humidExt1, "maxhumidExt", "maxHeurExt");
      thpmin(humidExt1, "minhumidExt", "minHeurExt");
      thpmax(humidInt1, "maxhumidInt", "maxHeurInt");
      thpmin(humidInt1, "minhumidInt", "minHeurInt");
      thpmax(humidCave1, "maxhumidCave", "maxHeurCave");
      thpmin(humidCave1, "minhumidCave", "minHeurCave");
    }
    if (document.getElementById("myChartPress") != null) {
      thpmax(pressExt1, "maxpressExt", "maxHeurExt");
      thpmin(pressExt1, "minpressExt", "minHeurExt");
    }
    console.log(tempExt1);
    console.log(humidInt1);
    console.log(humidCave1);
    console.log(pressExt1);
    console.log(heure);
    if (document.getElementById("myChartTemp") != null) {
      var ctx1 = document.getElementById("myChartTemp").getContext("2d");
      new Chart(ctx1, {
        type: "line",
        data: {
          labels: heure,
          datasets: [
            {
              label: "Tempetrature extérieur",
              data: tempExt1,
              borderWidth: 1,
            },
            {
              label: "Tempetrature interieur",
              data: tempInt1,
              borderWidth: 1,
            },
            {
              label: "Tempetrature cave",
              data: tempCave1,
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
          labels: heure,
          datasets: [
            {
              label: "Humidité extérieur",
              data: humidExt1,
              borderWidth: 1,
            },
            {
              label: "Humidité interieur",
              data: humidInt1,
              borderWidth: 1,
            },
            {
              label: "Humidité cave",
              data: humidCave1,
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
          labels: heure,
          datasets: [
            {
              label: "Pression atmosphérique",
              data: pressExt1,
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

  json24();
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
