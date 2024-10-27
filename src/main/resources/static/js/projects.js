$(function() {

    // Load All Projects
    $.getJSON('http://localhost:8080/api/v1/projects', function(response) {
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
                            <button type="button" data-id="${full.id}" class="edit-project btn btn-sm btn-warning">Редактирай</button>
                            <button type="button" data-id="${full.id}" class="delete-project btn btn-sm btn-danger">Изтрий</button>
                        `;
                    }
                }
            ]
        });
    });

    // Listen for click on creating new project
    $(document).on('click', '#createNewProject', function (){
        let formData = $('#newProjectForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/v1/projects',
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

    // Delete project
    $(document).on('click', '.delete-project', function () {
        if(confirm("Сигурен ли си, че искаш да изтриеш този запис?")) {
            let id = $(this).data('id');

            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/api/v1/projects/' + id,
                contentType: "application/json",
                dataType: 'json',
                success: function (response) {
                    window.location.reload();
                }
            });
        }
    });

    // Edit project
    $(document).on('click', '.edit-project', function () {
        $('#editFormModal').modal('show');

        var id = $(this).data('id');

        $('#editProject').data('id', id);

        $.getJSON('http://localhost:8080/api/v1/projects/' + id, function(response) {
            $('#editProjectForm #title').val(response.title);
        });
    });

    // Listen for click on editing project
    $(document).on('click', '#editProject', function (){
        var id = $(this).data('id');

        let formData = $('#editProjectForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/api/v1/projects/' + id,
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

});