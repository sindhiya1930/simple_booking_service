/**
 * Created by Кира on 15.08.2017.
 */


var ajaxUrl = "/hotel_manager/object/bookings/";
var datatableApi;


function updateManagerBookingTableByDatesAdded() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "between_dates",
        data: $("#bookingsManagerDatesAddedFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerBookingTableByInDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_in_date",
        data: $("#bookingsManagerInDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerBookingTableByOutDate() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_out_date",
        data: $("#bookingsManagerOutDateFilter").serialize(),
        success: updateTableByData
    });
}
function updateManagerBookingTableByUserId() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "by_user_id",
        data: $("#bookingsManagerUserIdFilter").serialize(),
        success: updateTableByData
    });
}

function clearBookingDatesAddedManagerFilter() {
    $("#bookingsManagerDatesAddedFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearBookingInDateManagerFilter() {
    $("#bookingsManagerInDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearBookingOutDateManagerFilter() {
    $("#bookingsManagerOutDateFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}
function clearBookingUserIdManagerFilter() {
    $("#bookingsManagerUserIdFilter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}


function deactivateManagerBooking(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: ajaxUrl + id,
        type: 'POST',
        data: 'active=' + enabled,
        success: function () {
            chkbox.closest('tr').toggleClass('disabled');
            successNoty(enabled ? 'common.activated' : 'common.deactivated');
        },
        error: function () {
            $(chkbox).prop("checked", !enabled);
        }
    });
}


$(function () {
    datatableApi = $('#hotelBookingsDatatable').DataTable(extendsOpts({
        "columns": [
            {
                "data": "id",
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        return '<a class="btn btn-default" href="/edit_booking?id=' + data + '">'
                            + '<span class="glyphicon glyphicon-share" aria-hidden="true"></span>' +
                            '&nbsp;&nbsp;&nbsp;' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "data": "dateAdded"
            },
            {
                "data": "inDate"
            },
            {
                "data": "outDate"
            },
            {
                "data": "userName"
            },
            {
                "data": "userEmail"
            },
            {
                "data": "userPhone"
            },
            {
                "data": "active",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="deactivateManagerBooking($(this),' + row.id + ');"/>';
                    }
                    return data;
                }
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "pageLength": 50,
        "createdRow": function (row, data, dataIndex) {
            if (!data.active) {
                $(row).addClass("disabled");
            }
        }
    }));
});



