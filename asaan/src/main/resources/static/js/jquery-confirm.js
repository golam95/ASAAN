var remove_alert = function(){
    $(".flash-alert").delay(5500).slideUp(500, function () {
        $(this).alert('close');
    });
};
// var showAlert = function (alertContent) {
//     $.alert({
//         title: 'Info',
//         content: alertContent,
//     });
// };

var showAlert = function (alertContent) {
    new PNotify({
        title: 'Info',
        text: alertContent,
        type: 'info',
        styling: 'bootstrap3'
    });
}

var showAlertByType = function (alertContent, type) {

    var alertType = 'info';
    var alertTitle = 'Info';

    if (type == "S") {
        alertTitle = 'Success!';
        alertType = 'success';
    }

    else if (type == "W") {
        alertTitle = 'Warning!';
        alertType = 'warning';
    }

    else if (type == 'F') {
        alertTitle = 'Error!';
        alertType = 'error';
    }

    new PNotify({
        title: alertTitle,
        text: alertContent,
        type: alertType,
        styling: 'bootstrap3'
    });
}

var confirmBlueDialog = function (text) {

    $.confirm({
        title: 'Confirm!',
        content: text,
        type: 'blue',
        typeAnimated: true,
        buttons: {
            confirm: {
                btnClass: 'btn-info',
                keys: ['enter'],
                action: function () {
                    $("form").submit();
                }
            },
            cancel: function () {

            }
        }
    });
};

var confirmDialog = function (text) {

    $.confirm({
        title: 'Confirm!',
        content: text,
        buttons: {
            confirm: {
                btnClass: 'btn-info',
                keys: ['enter'],
                action: function () {
                    $("form").submit();
                }
            },
            cancel: function () {

            }
        }
    });
};

// confirmDialog("Sure to proceed?");

