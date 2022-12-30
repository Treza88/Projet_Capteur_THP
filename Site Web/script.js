const tempExt1 =[]
var tempExt2 = []
var tempInt1 =[]
var tempCave1 =[]
var dateServer1 =[]

document.addEventListener('DOMContentLoaded', function() {
json24();
async function json24(){

     const response = await fetch('http://localhost:8080/api/temps');

    data = await response.json();
                            
      var i=0;
      for (let r of data){
        tempExt1[i]=r.tempExt;
        tempInt1[i]=r.tempInt;
        tempCave1[i]=r.tempCave;
        dateServer1[i]=r.dateServer;
        i+=1
      }
      var heure = [];
      for(let i of dateServer1){
heure.push(i.slice(11,13)+"h");
      }

      console.log(heure);
          var ctx = document.getElementById('myChart').getContext('2d');
        
          new Chart(ctx, {
            type: 'line',
            data: {
              labels: heure.reverse(),
              datasets: [
                {
                label: 'Tempetrature extérieur',
                data: tempExt1.reverse(),
                borderWidth: 1
              },
              {
                label: 'Tempetrature interieur',
                data: tempInt1.reverse(),
                borderWidth: 1
              },
              {
                label: 'Tempetrature cave',
                data: tempCave1.reverse(),
                borderWidth: 1
              },
            ]
            },
            options: {
              responsive: true,
              scales: {
                y: {
                  suggestedMin: 20,
                  beginAtZero: true
                }
              }
            }
          });
         };
  });
 

  




