<!DOCTYPE html>
<html>
<body style="padding: 20px;">
	<h2>Meesage; ${model}</h2>
	<table style="margin-bottom: 20px; table-border: 1px;">
		<tr>
			<th style="width: 100px;">Name:</th>
			<td>${jspModel.name}</td>
		</tr>
		<tr>
			<th>Id:</th>
			<td>${jspModel.id}</td>
		</tr>
<tr>
		<form action="setModel" method="post" style="border: 1px solid black;">
		<p/>
			<table>
				<tr>
					<th style="width: 100px;">Name:</th>
					<td><input type="text" name="name" ></td>
				</tr>
				<tr>
					<th>Id number:</th>
					<td><input type="text" name="id" ></td>
				</tr>
			</table>
			
			<button type="submit">Save</button>
			
		</form>
		</tr>
	</table>
	
</body>
</html>
