/**
 * Created by Кира on 15.08.2017.
 */

var form;

function makeEditable() {
    form = $('.detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({ cache: false });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function extendsOpts(opts) {
    $.extend(true, opts,
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "autoWidth": false,
            responsive: true,
            "paging": true,
            "info": true,
            "language": {
                "search": i18n["common.search"],
                "processing": i18n["common.processing"],
                "info": i18n["common.table_info"],
                "lengthMenu":    i18n["common.menu_length"],
                "loadingRecords": i18n["common.loading_records"],
                "zeroRecords":    i18n["common.zero_records"],
                "emptyTable":     i18n["common.empty_table"],
                "paginate": {
                    "first":      i18n["common.paging_first"],
                    "previous":   i18n["common.paging_previous"],
                    "next":       i18n["common.paging_next"],
                    "last":       i18n["common.paging_last"]
                }
            },
            "initComplete": makeEditable
        }
    );
    return opts;
}


function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function getDelayedTable() {
    $.get(ajaxUrl, updateTableByData);
}

function delayedUpdate() {
    setTimeout(getDelayedTable, 2000);
}



var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: i18n[key],
        type: 'success',
        layout: 'bottomRight',
        timeout: 10000
    });
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: i18n['common.errorStatus'] + ': ' + jqXHR.status + '<br>'+ errorInfo.cause + '<br>' + errorInfo.details.join("<br>"),
        type: 'error',
        layout: 'bottomRight'
    });
}



function renderExpandBtn(data, type, row) {
    if (type === 'display') {
        return '<span><i class="fa fa-bars" aria-hidden="true"></i></span>';
    }
}



//    ADMIN    -------------------------------------------------------------------------------------------------------



function addAptType() {
    $('#aptTypeModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.load-bar').hide();
    $('#aptTypeEditRow').modal();
}

function updateAptTypeRow(id) {
    $('#aptTypeModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#aptTypeEditRow').modal();
    });
}

function renderAptTypeEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateAptTypeRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveAptType() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#aptTypeEditRow').modal('hide');
            updateAptTypesTable();
            successNoty('common.saved');
        }
    });
}

function renderAptTypeDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteAptTypeRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteAptTypeRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateAptTypesTable();
            successNoty('common.deleted');
        }
    });
}



function addHotel() {
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    form.find("input[name='" + 'managerId' + "']").val('100003');
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
    $('.load-bar').hide();
    $('#hotelEditRow').modal();
}

function updateHotelRow(id) {
    $('#hotelModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
            $('.cityNameForm :input').attr('readonly','readonly');
            $('.countryNameForm :input').attr('readonly','readonly');
        });
        $('.load-bar').hide();
        $('#hotelEditRow').modal();
    });
}

function renderHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveHotel() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#hotelEditRow').modal('hide');
            updateHotelsTableByRating();
            updateHotelsTableByCity();
            successNoty('common.saved');
        }
    });
}

function renderHotelDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteHotelRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateHotelsTableByCity();
            updateHotelsTableByRating();
            successNoty('common.deleted');
        }
    });
}



function addRegion() {
    $('#regionModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.currentCountryName').hide();
    $('.countryNamesList').show();
    $('.load-bar').hide();
    $('#regionEditRow').modal();
}

function updateRegionRow(id) {
    $('#regionModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("textarea[name='" + key + "']").val(value);
            form.find("input[name='" + key + "']").val(value);
            $('.countryNamesList').hide();
            $('.currentCountryName').show();
        });
        $('.load-bar').hide();
        $('#regionEditRow').modal();
    });
}

function renderRegionEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateRegionRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function renderRegionImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateRegionImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function saveRegion() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#regionEditRow').modal('hide');
            updatePlacesTable();
            successNoty('common.saved');
        }
    });
}

function updateRegionImage(id) {
    $('#imgTag').attr('src', '');
    $('#regionImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#regionImageEditRow').modal();
    });
}


function saveRegionImage() {
    $('.load-bar').show();
    var objFormData = new FormData(document.getElementById("imgForm"));
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'set_image',
        data: objFormData,
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        //data: form.serialize(),
        success: function () {
            $('#regionImageEditRow').modal('hide');
            updatePlacesTable();
            successNoty('common.saved');
        }
    });
}

function renderRegionDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteRegionRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteRegionRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updatePlacesTable();
            successNoty('common.deleted');
        }
    });
}



function addUser() {
    $('#userUpdateModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.userRoleInput').show();
    $('.load-bar').hide();
    $('#enabled').remove();
    $('#userEditRow').modal();
}

function updateUserRow(id) {
    $('#userUpdateModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        var userForm = $(".detailsForm");
        if(!$('#enabled').length) {
            userForm.append('<input type="hidden" id="enabled" name="enabled">');
        }
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            $('.userRoleInput').hide();
        });
        $('.load-bar').hide();
        $('#userEditRow').modal();
    });
}

function renderUserEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateUserRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function saveUser() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#userEditRow').modal('hide');
            updateUsersTable();
            successNoty('common.saved');
        }
    });
}

function renderUserDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteUserRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteUserRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateUsersTable();
            successNoty('common.deleted');
        }
    });
}




function renderDeleteVoteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteVoteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteVoteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateVotesTable();
            successNoty('common.deleted');
        }
    });
}



//    MANAGER -------------------------------------------------------------------------------------------------------



function addApartment() {
    $('#apartmentModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    $('.load-bar').hide();
    $('#apartmentEditRow').modal();
}

function renderApartmentEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateApartmentRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function updateApartmentRow(id) {
    $('#apartmentModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#apartmentEditRow').modal();
    });
}

function renderApartmentImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateApartmentImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateApartmentImage(id) {
    $('#imgTag').attr('src', '');
    $('#apartmentImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#apartmentImageEditRow').modal();
    });
}

function saveApartment() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#apartmentEditRow').modal('hide');
            updateApartmentsTable();
            successNoty('common.saved');
        }
    });
}

function saveApartmentImage() {
    $('.load-bar').show();
    var objFormData = new FormData(document.getElementById("imgForm"));
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'set_image',
        data: objFormData,
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        //data: form.serialize(),
        success: function () {
            $('#apartmentImageEditRow').modal('hide');
            updateApartmentsTable();
            successNoty('common.saved');
        }
    });
}

function renderApartmentDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteApartmentRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteApartmentRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateApartmentsTable();
            successNoty('common.deleted');
        }
    });
}




function addManagerHotel() {
    $('#managerHotelModalTitle').html(i18n["addTitle"]);
    form.find(":input").val("");
    form.find("textarea[name='" + 'description' + "']").val("");
    $('.cityNameForm :input').removeAttr('readonly');
    $('.countryNameForm :input').removeAttr('readonly');
    $('.load-bar').hide();
    $('#managerHotelEditRow').modal();
}

function renderManagerHotelEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateManagerHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelRow(id) {
    $('#managerHotelModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
            $('.cityNameForm :input').attr('readonly','readonly');
            $('.countryNameForm :input').attr('readonly','readonly');
        });
        $('.load-bar').hide();
        $('#managerHotelEditRow').modal();
    });
}

function renderManagerHotelImageBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-default" onclick="updateManagerHotelImage(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-picture" aria-hidden="true"></span></a>';
    }
}

function updateManagerHotelImage(id) {
    $('#imgTag').attr('src', '');
    $('#managerHotelImageModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key === 'imgPath') {
                $('#imgTag').attr('src', value);
            }
            form.find("input[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#managerHotelImageEditRow').modal();
    });
}

function saveManagerHotel() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#managerHotelEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}

function saveManagerHotelImage() {
    $('.load-bar').show();
    var objFormData = new FormData(document.getElementById("imgForm"));
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'set_image',
        data: objFormData,
        enctype: 'multipart/form-data',
        contentType: false,
        processData: false,
        //data: form.serialize(),
        success: function () {
            $('#managerHotelImageEditRow').modal('hide');
            updateManagerHotelsTable();
            successNoty('common.saved');
        }
    });
}

function renderManagerHotelDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteManagerHotelRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteManagerHotelRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateManagerHotelsTable();
            successNoty('common.deleted');
        }
    });
}





// USER  ---------------------------------------------------------------------------------------------------------------



function renderGetUserVoteByHotelBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateUserVoteByHotelId(' + row.id + ');">' +
            '<span><i class="fa fa-commenting" aria-hidden="true"></i> Vote Object</span></a>';
    }
}

function updateUserVoteByHotelId(id) {
    $('#userHotelVoteModalTitle').html(i18n["editTitle"]);
    $.get('/user/votes/vote_object?id=' + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#userHotelVoteEditRow').modal();
    });
}

function createUserVoteByHotel() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: '/user/votes/create_update',
        data: form.serialize(),
        success: function () {
            $('#userHotelVoteEditRow').modal('hide');
            updateUserHotelsTable();
            successNoty('common.saved');
        }
    });
}



function renderUserVoteEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-warning" onclick="updateUserVoteRow(' + row.id + ');">' +
            '<span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>';
    }
}

function updateUserVoteRow(id) {
    $('#userVoteModalTitle').html(i18n["editTitle"]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            form.find("textarea[name='" + key + "']").val(value);
        });
        $('.load-bar').hide();
        $('#userVoteEditRow').modal();
    });
}

function saveUserVote() {
    $('.load-bar').show();
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'create_update',
        data: form.serialize(),
        success: function () {
            $('#userVoteEditRow').modal('hide');
            updateUserVotesTable();
            successNoty('common.saved');
        }
    });
}

function renderUserVoteDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-danger" onclick="deleteUserVoteRow(' + row.id + ');">'+
            '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
    }
}

function deleteUserVoteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateUserVotesTable();
            successNoty('common.deleted');
        }
    });
}