/**
 * Created by Кира on 07.09.2017.
 */

var form;

function extendsUserOpts(opts) {
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


function updateUserTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}


function getDelayedTable() {
    $.get(ajaxUrl, updateUserTableByData);
}

function delayedUpdate() {
    setTimeout(getDelayedTable, 2000);
}


function renderExpandBtn(data, type, row) {
    if (type === 'display') {
        return '<span><i class="fa fa-bars" aria-hidden="true"></i></span>';
    }
}






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