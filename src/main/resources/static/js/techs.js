$(function() {

    // Load All Techs
    $.getJSON('http://localhost:8080/api/v1/techs', function(response) {
        $('#myTable').dataTable({
            processing: true,
            data: response,
            aaSorting: [[ 0, "desc" ]],
            columns: [
                { data:  "id" },
                { data:  "title" },
                {
                    data:  "createdAt",
                    render: function (data, type, full, meta) {
                        return data + " ч.";
                    }
                },
                {
                    data: null,
                    render: function (data, type, full, meta) {
                        return `
                            <button type="button" data-id="${full.id}" class="edit-tech btn btn-sm btn-warning">Редактирай</button>
                            <button type="button" data-id="${full.id}" class="delete-tech btn btn-sm btn-danger">Изтрий</button>
                        `;
                    }
                }
            ]
        });
    });

    // Listen for click on creating new tech
    $(document).on('click', '#createNewTech', function (){
        let formData = $('#newTechForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/v1/techs',
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

    // Delete tech
    $(document).on('click', '.delete-tech', function () {
        if(confirm("Сигурен ли си, че искаш да изтриеш този запис?")) {
            let id = $(this).data('id');

            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/api/v1/techs/' + id,
                contentType: "application/json",
                dataType: 'json',
                success: function (response) {
                    window.location.reload();
                }
            });
        }
    });

    // Edit tech
    $(document).on('click', '.edit-tech', function () {
        $('#editFormModal').modal('show');

        var id = $(this).data('id');

        $('#editTech').data('id', id);

        $.getJSON('http://localhost:8080/api/v1/techs/' + id, function(response) {
            $('#editTechForm #title').val(response.title);
        });
    });

    // Listen for click on editing tech
    $(document).on('click', '#editTech', function (){
        var id = $(this).data('id');

        let formData = $('#editTechForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/api/v1/techs/' + id,
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

});