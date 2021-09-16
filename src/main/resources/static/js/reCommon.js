//公共AJAX
function getData(type, url, data, dataType, success) {
    $.ajax({
        type: type,
        url: url,
        cache: false,
        data: data,
        dataType: dataType,
        success: success,
        headers: {
            "Authorization": localStorage.getItem('token')
        }
    });
}
function getData_async(type, url, data, dataType, success) {
    $.ajax({
        type: type,
        url: url,
        cache: false,
        data: data,
        dataType: dataType,
        success: success,
        async: false,
        headers: {
            "Authorization": localStorage.getItem('token')
        }
    });
}