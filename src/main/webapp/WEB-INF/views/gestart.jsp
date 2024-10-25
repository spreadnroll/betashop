<!doctype html>
<html lang="en">
  <%@ include file="common/head.jspf" %>
 
  <body>
   
  <%@ include file="common/navbar.jspf" %>
   
   <section class="content-main" style="max-width: 760px">
   
   	<div class="content-header">
   		<h2 class="content-title">${title}</h2>
   		 <div>
		    <a href="#" class="btn btn-outline-danger">x Annulla</a>
		 </div>
   	</div>
   	
   	<div class="card mb-4">
   		<div class="card-body">
   			
   			<form:form  method="POST" modelAttribute="datiart">
   			<form:errors path="*" cssClass="alert alert-danger" element="div"/> 
   				
   			<div class="row gx-2">
   			
			    <!-- Codice Articolo --> 
			    <fieldset class="col-sm-6 mb-3">	
			       <form:label for="codice" path="codart" cssClass="form-label">Codice Articolo:</form:label>
			  	   <form:input id="codice" path="codart" type="text" cssClass="form-control" placeholder="Codice Articolo"/>
			   	   <form:errors path="codart" cssClass="text-danger"/> 	
			    </fieldset>
			    <!-- Barcode -->
			    <div class="col-sm-6 mb-3">
			      <label for="ean" class="form-label">Barcode:</label>
			      	<select name="ean" class="form-select" >
			      </select>
			    </div>
			    
			</div>
			
			<!--Descrizione -->
		    <fieldset class="mb-4">
		    	<form:label for="descrizione" path="descrizione" cssClass="form-label">Descrizione</form:label>
		    	<form:input id="descrizione" path="descrizione" type="text" cssClass="form-control" placeholder="Descrizione Articolo"/>
		    	<form:errors path="descrizione" cssClass="text-danger"/>
		    </fieldset>  
		    
		    <div class="row gx-2">
		    
		    	<!--Unit� di misura-->
			    <fieldset class="col-sm-6 mb-3">
			      <form:label for="um" path="um" cssClass="form-label">Unit� di Misura</form:label>
			      <form:select path="um" cssClass="form-select">
					<form:option value="PZ" label="Pezzi"/>
					<form:option value="LT" label="Litri"/>
					<form:option value="KG" label="Kilogrammi"/>
				  </form:select>
			    </fieldset>
			    
			     <!-- Iva -->
			    <fieldset class="col-sm-6 mb-3">
			     <form:label for="iva" path="idIva" class="form-label">Iva:</form:label>
			     <form:select id="iva" path="idIva" class="form-select">
			     	 <form:option value="${null}" label="--Seleziona Iva--" />
			     	 <c:forEach var="vats" items="${iva}">
			              <form:option value="${vats.idIva}" label="${vats.descrizione}"/>
			         </c:forEach>  
			         <form:errors path="idIva" cssClass="text-danger"/> 
				 </form:select>
			    </fieldset>
		    
		    </div>
		    
		     <div class="mb-4">
		     	<div class="row gx-2">
		     		
		     	 <!-- Pezzi per Cartone --> 	
		         <fieldset class="col-3">
		          	<form:label for="pzcart" path="pzcart" cssClass="form-label">Pezzi per Cartone:</form:label>
		            <form:input id="pzcart" path="pzcart" type="number" cssClass="form-control"/>  
		          </fieldset>
		          
		           <!-- Peso Netto --> 	
		          <fieldset class="col-3">
		          	<form:label for="peso" path="peso" cssClass="form-label">Peso Netto:</form:label>
		            <form:input id="peso" path="peso" type="text" cssClass="form-control"/>  
		          </fieldset>
		          
		           <!--Prezzo --> 	
		          <fieldset class="col-4">
		          	<label class="form-label">Prezzo:</label>
		      		<form:input placeholder="Prezzo"  path="prezzo" type="text" class="form-control" />
		    	  </fieldset>
		    	  
		    	  <!-- Valuta -->
		    	  <div class="col-2">
			       <label class="form-label">Valuta:</label>
			       <select class="form-select">
			        <option> EUR </option>
			        <option> USD </option>
			        <option> GBP </option>
			       </select>
		      	  </div>	
			     	
		     	</div>
		     </div>
		     
		     <div class="row gx-2">
			    <!-- Categoria -->
			    <fieldset class="col-sm-6 mb-3">
			      <form:label for="pesoNetto" path="idCat" cssClass="form-label">Categoria:</form:label>
			      <form:select path="idCat" cssClass="form-select">
					 <form:options items="${famAssort}"  itemValue="id" itemLabel="descrizione" />
				  </form:select> 
			    </fieldset>
			   	
			   	<!-- Stato Articolo  -->
			    <fieldset class="col-sm-6 mb-3">
			      <form:label for="status" path="idStatoArt" cssClass="form-label">Stato Articolo:</form:label>
			      <form:select path="idStatoArt" cssClass="form-select" >
					<form:option value="1" label="Attivo"/>
					<form:option value="2" label="Sospeso"/>
					<form:option value="3" label="Eliminato"/>
				  </form:select>
			     </fieldset>
			 </div> <!-- row.// -->
			 
			  <!-- Immagine -->
		     <div class="mb-4">
		      <label class="form-label">Immagine Articolo:</label>
		      <input class="form-control" type="file" name="file" />
		     </div>
		   
		     <br>
		   
		     <input type="submit" id="btnAdd" class="btn btn-primary form-buttons" value = "Salva Dati" />
	

   			</form:form>
   		
   		</div>
   	</div>
   	
   	
   </section>
 
 
  <%@ include file="common/foot.jspf" %>
  </body>
</html>