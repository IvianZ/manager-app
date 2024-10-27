$(function() {

    let loadSelectOptions = function(dropdownParent, projects = [], techs = []) {

        setTimeout(function (){
            $(dropdownParent + ' .projectSelect2').empty().trigger('change');
            $(dropdownParent + ' .techSelect2').empty().trigger('change');

            $.getJSON('http://localhost:8080/api/v1/projects', function(response) {
                let results = response.map(function (obj) {
                    return {id: obj.id, text: obj.title, selected: projects.includes(obj.id)};
                });

                $(dropdownParent + ' .projectSelect2').select2({
                    placeholder: '- Изберете проект -',
                    data: results,
                    dropdownParent: $(dropdownParent)
                });
            });

            $.getJSON('http://localhost:8080/api/v1/techs', function(response) {
                let results = response.map(function (obj) {
                    return {id: obj.id, text: obj.title, selected: techs.includes(obj.id)};
                });

                $(dropdownParent + ' .techSelect2').select2({
                    placeholder: '- Изберете технология -',
                    data: results,
                    dropdownParent: $(dropdownParent)
                });

            });
        }, 200);
    };

    $(document).on('click', '#openEmployeeModal', function (e){
        e.preventDefault();

        $('#addFormModal').modal('show');

        loadSelectOptions("#addFormModal", [], []);
    });

    // Load All Employees
    $.getJSON('http://localhost:8080/api/v1/employees', function(response) {
        $('#myTable').dataTable({
            processing: true,
            data: response,
            aaSorting: [[ 0, "desc" ]],
            columns: [
                { data:  "id" },
                { data:  "firstName" },
                { data: "middleName" },
                { data: "lastName" },
                { data: "email" },
                { data: "phone" },
                { data: "position" },
                {
                    data: null,
                    render: function (data, type, full, meta) {
                        return `
                            <button type="button" data-id="${full.id}" class="view-employee btn btn-sm btn-info">Преглед</button>
                            <button type="button" data-id="${full.id}" class="edit-employee btn btn-sm btn-warning">Редактирай</button>
                            <button type="button" data-id="${full.id}" class="delete-employee btn btn-sm btn-danger">Изтрий</button>
                        `;
                    }
                }
            ]
        });
    });

    // Listen for click on creating new employee
    $(document).on('click', '#createNewEmployee', function (){
        let formData = $('#newEmployeeForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        formData['projectIds'] = $('#newEmployeeForm .projectSelect2').select2("val").map(Number);
        formData['techIds'] = $('#newEmployeeForm .techSelect2').select2("val").map(Number);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/v1/employees',
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

    // Delete employee
    $(document).on('click', '.delete-employee', function () {
        if(confirm("Сигурен ли си, че искаш да изтриеш този запис?")) {
            let id = $(this).data('id');

            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/api/v1/employees/' + id,
                contentType: "application/json",
                dataType: 'json',
                success: function (response) {
                    window.location.reload();
                }
            });
        }
    });

    // View employee
    $(document).on('click', '.view-employee', function () {
        $('#employeeDetails').html('')
        $('#viewEmployeeModal').modal('show');

        var id = $(this).data('id');

        $.getJSON('http://localhost:8080/api/v1/employees/' + id, function(response) {
            $('#employeeDetails').html(`
                <strong>Име:</strong> ${response.firstName}<br/>
                <strong>Презиме:</strong> ${response.middleName}<br/>
                <strong>Фамилия:</strong> ${response.lastName}<br/>
                <strong>E-Mail:</strong> ${response.email}<br/>
                <strong>Телефонен номер:</strong> ${response.phone}<br/>
                <strong>Позиция:</strong> ${response.position}<br/>
                <strong>Домейн юзър:</strong> ${response.domainUser}<br/>
                <strong>Име на компютър:</strong> ${response.computerName}<br/><br/>
                
                <table>
                    <tr>
                        <td><strong>Проекти:</strong></td>
                        <td>${response.projects.map(function (obj) { return obj.title; }).join(', ')}</td>
                    </tr>
                    <tr>
                        <td><strong>Технологии:</strong></td>
                        <td>${response.techs.map(function (obj) { return obj.title; }).join(', ')}</td>
                    </tr>
                </table>
            `)
        });
    });

    // Edit employee
    $(document).on('click', '.edit-employee', function () {
        $('#editFormModal').modal('show');

        var id = $(this).data('id');

        $('#editEmployee').data('id', id);


        $.getJSON('http://localhost:8080/api/v1/employees/' + id, function(response) {
            $('#editEmployeeForm #name').val(response.firstName);
            $('#editEmployeeForm #middle_name').val(response.middleName);
            $('#editEmployeeForm #surname').val(response.lastName);
            $('#editEmployeeForm #email').val(response.email);
            $('#editEmployeeForm #domain_user').val(response.domainUser);
            $('#editEmployeeForm #computer_name').val(response.computerName);
            $('#editEmployeeForm #phone').val(response.phone);
            $('#editEmployeeForm #position').val(response.position);

            loadSelectOptions(
                "#editFormModal",
                response.projects.map(function (obj) { return obj.id; }),
                response.techs.map(function (obj) { return obj.id; })
            );
        });
    });

    // Listen for click on editing employee
    $(document).on('click', '#editEmployee', function (){
        var id = $(this).data('id');

        let formData = $('#editEmployeeForm').serializeArray().reduce(function(obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        formData['projectIds'] = $('#editEmployeeForm .projectSelect2').select2("val").map(Number);
        formData['techIds'] = $('#editEmployeeForm .techSelect2').select2("val").map(Number);

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/api/v1/employees/' + id,
            data: JSON.stringify(formData),
            contentType: "application/json",
            dataType: 'json',
            success: function (response) {
                window.location.reload();
            }
        });
    });

});