
 function getCurrentURL () {
   return window.location.href
 }

//console.log(getCurrentURL ().includes("/exterieur"));
window.onload=function()   {
if (getCurrentURL ().includes("admin/index")){
 var b = document.querySelector('[value="Accueil"]');
 b.setAttribute("id", "en-cours");
 document.title = "Accueil";}
 else if (getCurrentURL ().includes("/exterieur")){
 var b = document.querySelector('[value="Exterieur"]');
 b.setAttribute("id", "en-cours");
  document.title = "Exterieur";}
 else if (getCurrentURL ().includes("/interieur")){
  var b = document.querySelector('[value="Interieur"]');
  b.setAttribute("id", "en-cours");
   document.title = "Interieur";}
 else if (getCurrentURL ().includes("/cave")){
  var b = document.querySelector('[value="Cave"]');
  b.setAttribute("id", "en-cours");
   document.title = "Cave";}
  else if (getCurrentURL ().includes("/users")){
   var b = document.querySelector('[value="Users"]');
   b.setAttribute("id", "en-cours");
    document.title = "Users";}


//$(document).ready(function(){
//    $(".dropdown-toggle").dropdown();
//});

//const dropdownBtn = document.getElementById("btn");
//    const dropdownMenu = document.getElementById("dropdown2");
//
//    const toggleDropdown = function () {
//      dropdownMenu.classList.toggle("show"); };
//
//    dropdownBtn.addEventListener("click", function (e) {
//      e.stopPropagation();
//      toggleDropdown();});

//    document.documentElement.addEventListener("click", function () {
//      if (dropdownMenu.classList.contains("show")) {
//        toggleDropdown();}
//    });






 }
