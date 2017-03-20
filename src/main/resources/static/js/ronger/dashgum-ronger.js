(function ($) {

    var links = document.querySelectorAll('a')

    Array.prototype.forEach.call(links, function (link) {
        link.addEventListener('click', function (evt) {
            evt.preventDefault()
            var curWwwPath = this.href;
            $("#main-content").load(curWwwPath);
        })
    })



})(jQuery);
