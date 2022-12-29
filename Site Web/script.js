
//function json24(){
  const container = document.getElementById("container")
  
  fetch('http://localhost:8080/api/temps')
    .then(response => response.json())
    //talert=commits[1].tempCave);
    .then(commits => {                                        
  var tempExt1 =[]
  var tempInt1 =[]
  var tempCave1 =[]
  var dateServer1 =[]
  for (let r of commits){
  tempExt1.push(r.tempExt);
  tempInt1.push(r.tempCave);
  tempCave1.push(r.dateServer);
  dateServer1.push(r.tempInt);
  alert(tempExt1);
  }
  //return tempExt1[0];
  })
  
 
//}
//alert(typeof json24())
alert(tempExt1)
console.log(json24())
//var dat=[12, 19, 3, 5, 2, 3]
document.addEventListener('DOMContentLoaded', function() {
  
  var ctx = document.getElementById('myChart');
  console.log(ctx);

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
      datasets: [{
        label: 'Tempetrature des dernière 24 heures',
        data: tempExt1,
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
});  


