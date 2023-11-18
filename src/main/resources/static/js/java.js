// var today = new Date;
// var min = today.getMinutes;
// var hh = today.getHours;
// var dd = today.getDate;
// var mm = today.getMonth;
// var yyyy = today.getFullYear;

// if(min < 10){
//     min = 0 + min;
// }
// if(hh < 10){
//     hh = 0 + hh;
// }

// if(dd < 10){
//     dd = 0 + dd;
// }

// if(mm < 10){
//     mm = 0 + mm;
// }

// today = yyyy + '-' + mm + '-' + dd + '-' + hh + '-' + min;

// document.getElementById("datetime").setAttribute("min", today);


document.getElementById("datetime").min = new Date().toISOString().split("T")[0];