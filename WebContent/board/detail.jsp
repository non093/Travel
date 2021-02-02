<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/side.jsp"></jsp:include>
<%	
	//댓글 시간
	
	
	int board_no = Integer.parseInt(request.getParameter("board_no"));

	//게시물 정보 및 조회수 증가
	BoardDao boardDao = new BoardDao();
	boardDao.plusReadcount(board_no);
	BoardDto boardDto = boardDao.find(board_no);
	
	//작성자가 필요
	MemberDao memberDao = new MemberDao();
	MemberDto writerDto = memberDao.find(boardDto.getBoard_nick());
	
	//댓글
	boolean isOwner = session.getAttribute("check") != null;
	
	String nick = request.getParameter("member_nick");
		
	int member_no = (int)session.getAttribute("check");
	
	MemberDto memberDto = memberDao.find(member_no);
	
	String member_nick = (String)session.getAttribute("nick");
	boolean isBoardOwner = memberDto.getMember_nick().equals(boardDto.getBoard_nick()); //작성자
	boolean map = boardDto.getBoard_address() != null;
	
%>

<%
//리플 목록
	ReplyDto replyDto = new ReplyDto();
	ReplyDao replyDao = new ReplyDao();
	List<ReplyDto> replyList = replyDao.select(board_no);
	
%>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c16001f42b911de2991dc18f6649856&libraries=services"></script>
<script type="text/javascript" src="<%=ctx %>/js/kakaomap.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', () => {
		document.querySelector('#replylist').querySelectorAll( '.reply_re' ).forEach( btn => {
		    btn.addEventListener( 'click', e => {
		        var options = 'top=10, left=10, width=570, height=350, scrollbars=no, status=no, menubar=no, toolbar=no, resizable=no';
		        var target = e.currentTarget.parentElement.parentElement.dataset.seqid-0;
		        var reply_board = <%=board_no%>;
		        window.open( '<%=ctx %>/board/rereply_insert.do?reply_no='+ target+'&reply_board=' +reply_board, 'replyForm', options); 
		    })
		})
	})
	
	$(function () {
            //여행목록용 (나중에 파일 추가 되면 조건문을 걸어서 처리)
            $(".tlist_btn").click(function() {
            	history.back();
            	
            });
            //자유게시판 목록용
            $(".flist_btn").click(function () {
            	location.href = "#";
            });
            //공지게시판용
            $(".nlist.btn").click(function() {
            	location.href ="#";
            });
            
			//글쓰러 가기
			$(".new_btn").click(function() {
				location.href = "<%=ctx %>/board/write.jsp";
			});
			//수정버튼
			$(".edit_btn").click(function() {
				location.href = "<%=ctx %>/board/edit.jsp?board_no=<%=board_no%>";
			});
			//삭제버튼
			
			//리플 수정, 삭제 버튼
			$(".reply_edit").hide();

            $(".reply_editbtn").click(function(e) {

                $(this).parents(".reply_normal").hide();
                $(this).parents(".reply_normal").next().show();
            });
            
            $(".cancelbtn").click(function(e) {
            	
            	$(this).parents(".reply_edit").hide();
            	$(this).parents(".reply_edit").prev().show();
            });
            
            
        });
 
</script>
<style>
 
        div {
            padding: 0.2rem;
        }

        .outbox {
            
            width: 800px;
            height: 800px;
            overflow-y: scroll;
        }

        .content {
            
            height: atuo;
            width: 600px;
            overflow: hidden;
            margin-top: 30px;
        }



        .outbox>.inbox {
            border: 1px solid blue;
            width: 400px;
            height: auto;
            background-color: #F6F6F6;
        }

        

        #map {
            border: 1px solid black;
            margin-top: 30px;
        }
        .replybox-write {
        	
        	vertical-align: middle;
        	margin-top: 30px;
        	width: 750px;
        	height: 150px;
        	display:inline-block;
        }
        .reply-table {
        border: 1px solid black;
        width: 700px;
        }
        .table-tableinfo {
				border:1px solid black;
				width:700px;
			}
		.reply_normal {
			width:700px;
			display:inline-block;
		}
		.recoinfo {
			float:left;
			
		}
		.right {
		float:right;
		
		}
		
   
</style>


	<div class="outbox">
		<a id="cl"></a>	
		<!-- (수정 삭제) -->
		<div class="new_btnlist">
			<button class="new_btn">새글</button>
		<% if(isBoardOwner) {%>
			<button class="edit_btn">수정</button>
			<button class="delete_btn"><a href="delete.do?board_no=<%=board_no%>">삭제</a></button>
		<% } %>
			<button class="tlist_btn">목록</button>
		
		
		</div>
		<div>
		 <b><font size="7">[<%=boardDto.getBoard_head()%>] <%=boardDto.getBoard_title() %></font></b>
		 <button class="input input-inline reportBoard-btn" style="float:right;">신고</button>
			<br><br>
			작성자 ▶ <%=boardDto.getBoard_nick()%>
			번호 ▶ <%=boardDto.getBoard_no() %>
			<br>
			조회 ▶ <%=boardDto.getBoard_view() %> 
			추천 ▶ <%=boardDto.getBoard_like() %>
			<br>
			작성시간 ▶<%=boardDto.getBoard_date() %>
		</div>
		<!-- 내용 -->
		<div class="content"><%=boardDto.getBoard_content() %></div>
		<!-- 지도 표시 -->
		<% if(map) {%> 
		<body onload="kakaomap();">
		<input type="hidden" name="board_address" class="input" value="<%=boardDto.getBoard_address() %>" onload="kakaomap();">
		<div id="map" style="width:600px; height:350px;"></div>
		</body>
		<% } %>
		<!-- (추천 버튼)-->
		<!--  댓글 목록 -->
		<div class="replybox" id="replylist">
			<a id="rl"></a>
			<%
				for (ReplyDto Dto : replyList) {
				boolean isReplyOwner = boardDto.getBoard_nick().equals(Dto.getReply_nick()); //게시판 작성자
				boolean isReply = Dto.getReply_nick().equals(memberDto.getMember_nick()); //리플 작성자
			%>
			
				<div class="reply_normal">
				<div class="recoinfo" id="reply" data-seqId="<%=Dto.getReply_no()%>"
					class="nickinfo">
					<%
						if (Dto.getReply_parent() == 0) {
					%>
						<font color="blue"><%=Dto.getReply_nick()%></font>
					<%
						} else if (Dto.getReply_parent() > 0) {
					%>

					<font color="green">&nbsp;&nbsp;&nbsp;&nbsp;<%=Dto.getReply_nick()%></font>>
					<%
						} 
					%>
						
					

					<content style="width:400px; min-height:50px; "><%=Dto.getReply_content()%></content>
					<%
						SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					String replydate = f.format(Dto.getReply_date());
					%>
					<font color="blue"><%=replydate%></font>

					<div class="replybtn right">
						<%
							if (isReply) {
						%>
						<input type="button" value="수정" class="reply_editbtn"> 
						<a href="reply_delete.do?reply_no=<%=Dto.getReply_no()%>&board_no=<%=board_no%>">삭제</a>
						<%
							} else if (!isReplyOwner && isReply && Dto.getReply_parent() > 0) {
						%>
						<input type="button" value="수정" class="reply_editbtn"> 
						<a href="reply_delete.do?reply_no=<%=Dto.getReply_no()%>&board_no=<%=board_no%>">삭제</a>

						<%
							} else if (Dto.getReply_parent() == 0) {
						%>
						<input type="button" value="답변" class="reply_re">
						<%
							}
						%>
					</div>
				</div>
			</div>
				
			

			<!-- 댓글 수정 -->
			<div class="reply_edit">
				<table>
				<tr>
				<td><%=member_nick%></td>
				<td>
				<form action="reply_edit.do" method="post">
				<input type="hidden" name="reply_no" value="<%=Dto.getReply_no()%>">
				<input type="hidden" name="reply_board" value="<%=board_no%>">
				<textarea style="width: 500px; height: 100px;" name="reply_content"><%=Dto.getReply_content()%></textarea>
				</td>
				<td>
						<input type="submit" value="댓글 수정" class="re_edit_btn"> 
						<input type="button" value="취소" class="cancelbtn">
					
				</form>
				</td>
				</table>
			</div>

			<%
				}
			%>
		</div>



		<!-- 댓글 작성 -->
		<div class="replybox-write">
			<table class="reply-table">
			<tr>
			<td style="width:20%;"><%=member_nick%></td>
			<td style="width:55%;">
			<form action="reply_insert.do" method="post">
				<input type="hidden" name="reply_board" value="<%=board_no%>">
				<textarea style="width: 500px; height: 100px; resize:none;" name="reply_content"></textarea>
			</td>
			<td style="width:20%;">
			<input type="submit" value="댓글 작성" class="rebtn">			
			</form>
			</td>
			</tr>
			</table>
		</div>


		<!-- (수정 삭제) -->
		<div class="new_btnlist">
			<button class="new_btn">새글</button>
		<% if(isBoardOwner) {%>
			<button class="edit_btn">수정</button>
			<button class="delete_btn"><a href="delete.do?board_no=<%=board_no%>">삭제</a></button>
		<% } %>
			<button class="tlist_btn">목록</button>
		
		
		</div>
	</div>
	
	<jsp:include page="/template/footer.jsp"></jsp:include>
