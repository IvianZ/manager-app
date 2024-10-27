$(function() {

    $.getJSON('http://localhost:8080/api/v1/reports/tech', function(response) {
        $('#reportTech').dataTable({
            processing: true,
            data: response,
            aLengthMenu: [5],
            lengthChange: false,
            aaSorting: [[ 1, "desc" ]],
            columns: [
                { data:  "techTitle" },
                { data:  "numberOfEmployees" }
            ]
        });
    });

    $.getJSON('http://localhost:8080/api/v1/reports/project', function(response) {
        $('#reportProjects').dataTable({
            processing: true,
            data: response,
            aLengthMenu: [5],
            lengthChange: false,
            aaSorting: [[ 1, "desc" ]],
            columns: [
                { data:  "projectTitle" },
                { data:  "numberOfEmployees" }
            ]
        });
    });

});