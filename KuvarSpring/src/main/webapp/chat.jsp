<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.my-custom-scrollbar {
	position: relative;
	overflow: auto;
}

.table-wrapper-scroll-y {
	display: block;
}

</style>
</head>
<body>


<%@ include file="header.jsp"%>
<div class="card rare-wind-gradient chat-room" style="width: 1300px; height: 700px; margin-top: 70px; margin-botom: 20px;">
  <div class="card-body">

    <!-- Grid row -->
    <div class="row px-lg-2 px-2">

      <!-- Grid column -->
      <div class="col-md-6 col-xl-4 px-0">

        <h6 class="font-weight-bold mb-3 text-center text-lg-left">Member</h6>
        <div class="white z-depth-1 px-2 pt-3 pb-0 members-panel-1 scrollbar-light-blue">
        
          <ul class="list-unstyled friend-list">
          
          <c:if test="${!empty chatUsers }">
          	<c:forEach items="${chatUsers }" var="u">
          		
          	  <li class="active grey lighten-3 p-2">
              	<a href="/Kuvar/controller/showMessages?idUser2=${u.idUser }" class="d-flex justify-content-between">
                <img src="${u.picture }" alt="avatar" class="avatar rounded-circle d-flex align-self-center mr-2 z-depth-1" style="width:50px; height: 60px;">
                <div class="text-small">
                  <strong>${u.name } ${u.surname }</strong>
                </div>
              </a>
            </li>
          	
          	</c:forEach>
    
          </c:if>
   
          </ul>
        </div>

      </div>
      <!-- Grid column -->

      <!-- Grid column -->
      <div class="col-md-6 col-xl-8 pl-md-3 px-lg-auto px-0" style="margin-top: 27px;">

        <div class="chat-message">
     
     	<c:if test="${!empty messages }">
     	
     	

          <ul class="list-unstyled table-wrapper-scroll-y my-custom-scrollbar" style="width: 450px; height: 450px">
          
          	
          		<c:forEach items="${messages }" var="m">
          		<li class="d-flex justify-content-between mb-4">
              		<img src="https://mdbootstrap.com/img/Photos/Avatars/avatar-6.jpg" alt="avatar" class="avatar rounded-circle mr-2 ml-lg-3 ml-0 z-depth-1" style="width: 40px; height: 40px;">
              		<div class="chat-body white p-3 ml-2 z-depth-1" style="width: 500px;">
                		<div class="header">
                  			<strong class="primary-font">${m.user1.name } ${m.user1.surname }</strong>
                		</div>
               			 <hr class="w-100">
                		<p class="mb-0">${m.content }</p>
              		</div>
            	</li>
          		</c:forEach>      
          </ul>
          
          
       </c:if>
        
        
        <form action="/Kuvar/controller/sendMessage" method="post">
          <div class="white" style="margin-top: 5px; position: relative;">
            <div class="form-group basic-textarea">
              <textarea class="form-control pl-2 my-0" id="exampleFormControlTextarea2" rows="3" placeholder="Type your message here..." name="content"></textarea>
            </div>
          </div>
          <input type="submit" class="btn btn-outline-pink btn-rounded btn-sm waves-effect waves-dark float-right" value="Send"></input>
        </form>        
        </div>

      </div>
      <!-- Grid column -->

    </div>
    <!-- Grid row -->

  </div>
</div>

</body>
</html>