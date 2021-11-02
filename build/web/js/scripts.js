/*!
 * Start Bootstrap - SB Admin v7.0.4 (https://startbootstrap.com/template/sb-admin)
 * Copyright 2013-2021 Start Bootstrap
 * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
 */
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

function createPagger(div, pageindex, gap, totalpage)
{
    var container = document.getElementById(div);
    //container.innerHTML = pageindex + ' ' + gap + ' '+ totalpage;
    if (pageindex - gap > 1)
        container.innerHTML += '<a href="list?page=1">First</a>';

    for (var i = pageindex - gap; i < pageindex; i++)
    {
        if (i > 0)
            container.innerHTML += '<a href="list?page=' + i + '">' + i + '</a>';
    }
    container.innerHTML += '<span>' + pageindex + '</span>';

    for (var i = pageindex + 1; i <= pageindex + gap; i++)
    {
        if (i <= totalpage)
            container.innerHTML += '<a href="list?page=' + i + '">' + i + '</a>';
    }
    if (pageindex + gap < totalpage)
        container.innerHTML += '<a href="list?page=' + totalpage + '">Last</a>';
}
