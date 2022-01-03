let main = {
    init: function () {
        let _this = this;

        $('#remittanceMonetary').on('change', function () {
            _this.visibleResult(false);
            _this.clearTransferAmount();
            _this.callCurrencyInfo();
        });
        $('#btn-save').on('click', function () {
            _this.calculateReceiptAmount();
        });
    },
    callCurrencyInfo: function () {

        main.loading();

        let data = {
            remittanceMonetary: $('#remittanceMonetary').val()
        };

        $.ajax({
            type: 'POST',
            url: '/currency/exchange/api/realtime-info',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            main.closeLoading();

            $('#exchangeAmount').val(data.exchangeAmount);
            $('#exchangeAmount').text(main.addCommaAndFixed(data.exchangeAmount));
            $('#exchangeMonetaryUnit').val(data.exchangeMonetaryUnit);
            $('#exchangeMonetaryUnit').text(data.exchangeMonetaryUnit);
        }).fail(function (error) {
            alert(JSON.stringify(error));
            main.closeLoading();
        });
    },
    calculateReceiptAmount: function () {
        let data = {
            remittanceMonetary: $('#remittanceMonetary').val(),
            exchangeAmount: $('#exchangeAmount').val(),
            exchangeMonetaryUnit: $('#exchangeMonetaryUnit').val(),
            transferAmount: $('#transferAmount').val(),
            receiptMonetary: $('#receiptMonetary').val()
        };

        $.ajax({
            type: 'POST',
            url: '/currency/exchange/api/calculate-receipt-amount',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            main.visibleResult(true, data.receiptAmount, data.receiptMonetaryUnit);

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    visibleResult: function (flag, receiptAmount, receiptMonetaryUnit) {
        let result = '<label>수취 금액은 ' + main.addCommaAndFixed(receiptAmount) + ' ' + receiptMonetaryUnit + ' 입니다.</label>';
        if (flag == false) {
            $('.result *').remove();
        } else {
            $('.result').append(result).show();
        }
    },
    clearTransferAmount: function () {
        $('#transferAmount').val('');
        $('#transferAmount').text('');
    },
    addCommaAndFixed: function (value) {
        return Number(value).toLocaleString(undefined, {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
    },
    loading: function () {
        let maskHeight = $(document).height();
        let maskWidth = window.document.body.clientWidth;

        let mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
        let loadingImg = '';

        loadingImg += " <div id='loadingImg'>";
        loadingImg += " <img src='../image/loading.gif' style='position:absolute; z-index:9500; text-align:center; display:block; margin-top:300px; margin-left:750px;'/>";
        loadingImg += "</div>";

        $('body').append(mask)

        $('#mask').css({
            'width': maskWidth,
            'height': maskHeight,
            'opacity': '0.3'
        });

        $('#mask').show();

        $('.loadingImg').append(loadingImg);
        $('#loadingImg').show();
        setTimeout("main.closeLoading()", 3000);
    },
    closeLoading: function () {
        $('#mask, #loadingImg').hide();
        $('#mask, #loadingImg').remove();
    }
};

main.init();

$(document).ready(function () {
    main.clearTransferAmount();
    main.callCurrencyInfo();
    main.visibleResult(false);
});