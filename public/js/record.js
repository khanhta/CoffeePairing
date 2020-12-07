$(document).ready(function() {
    $('form').on('submit', function(event) {

        var formData = {
            'name'      : $('input[name=name]').val(),
            'email'     : $('input[name=email]').val(),
            'timeslots'  : $('input[name=timeslot]').val()
        };

        $.ajax({
            type        : 'POST',
            url         : '/users/add',
            data        : formData,
            dataType    : 'json'
        })
        .done(function(data) {
            console.log(data);
            $('#result').html('<div class="alert alert-success">Your registration for '
                + data.timeslots + ' was successful</div>');
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(errorThrown);
        });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
    });
});