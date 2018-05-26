<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<section id="basicshareinfo_section" style="font-family:'Microsoft YaHei'">
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">返回</a>
        </nav>
        <h1 class="title">基础电流分摊</h1>
    </header>
    <article class="active" style="padding: 5px 0 5px; height: auto; overflow-y: scroll; position: absolute; top: 40px; bottom: 0px; width: 100%;font-size: 13px;">
        <div style="padding: 10px 2px 2px 2px;">
            <ul class="ul-form">
                <form>
                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                    &nbsp;
                    <input type="text" class="input-medium search-query" name="sisitename" placeholder="请输入站址名称..."
                           style="width:77%" size="4" value="${sisitename}">&nbsp;
                    <button class="btn">
                        <a id="searchHrefTop" onclick="searchValue(null)" href="javascript:void(0)" data-target="section">
                            <strong>查询</strong>
                        </a>
                    </button>
                </form>
            </ul>
        </div>
        <div>
            <ul class="list inset demo-list">
                <c:forEach items="${page.list}" var="bizBasicShareinfo">
                    <li data-icon="next" data-selected="selected">
                        <span class="icon newspaper"></span>
                        <a>
                            <strong>
                                <td style="white-space:nowrap;overflow:hidden;word-break:keep-all;">
                                        ${bizBasicShareinfo.sisitename}
                                </td>
                                <br/>
                                <td style="white-space:nowrap;overflow:hidden;word-break:keep-all;">
                                        ${bizBasicShareinfo.sisitenum}
                                </td>
                            </strong>
                        </a>
                    </li>
                </c:forEach>
                <li style="float: right;">
                    <span class="icon left">
                        <button class="btn">
                            <a id="searchHrefHome" onclick="searchValue('home')" href="javascript:void(0)" data-target="section">
                                <strong style="font-size: 14px;font-weight: bold;">首页</strong>
                            </a>
                        </button>
                    </span>
                    <span class="icon left">
                        <button class="btn">
                            <a id="searchHrefLeft" onclick="searchValue('left')" href="javascript:void(0)" data-target="section">
                                <strong style="font-size: 14px;font-weight: bold;">上一页</strong>
                            </a>
                        </button>
                    </span>
                    <span class="icon right">
                        <button class="btn">
                            <a id="searchHrefRight" onclick="searchValue('right')" href="javascript:void(0)" data-target="section">
                                <strong style="font-size: 14px;font-weight: bold;">下一页</strong>
                            </a>
                        </button>
                    </span>
                </li>
            </ul>
        </div>
    </article>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $('body').delegate('#basicshareinfo_section', 'pageinit', function () {
        });
        $('body').delegate('#basicshareinfo_section', 'pageshow', function () {
            var hash = J.Util.parseHash(location.hash);
            //console.info($(hash.param));
        });
        $(function() {
            searchValue(null);
        });

        function searchValue(pageIndex) {
            console.info(pageIndex);
            var url = "#basicshareinfo_section";
            var pageNo = $("#pageNo").val();
            if (pageIndex) {
                pageNo = parseInt(pageNo);
                if (pageIndex == 'home') {
                    if (pageNo > 1) {
                        pageNo = 1;
                    }
                }else if (pageIndex == 'left') {
                    if (pageNo > 1) {
                        pageNo = pageNo - 1;
                    }
                } else if (pageIndex == 'right') {
                    pageNo = pageNo + 1;
                }
            }
            url += "?pageNo=" + pageNo;
            var sisitename = $("input[name='sisitename']").val();
            if (sisitename) {
                url += "&sisitename=" + sisitename;
            }
            $("a[id^='searchHref']").attr("href", url);
            console.info(url);
        }
    </script>
</section>