
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Pessoa - Cadastro</title>
</head>
<body>

<h1>Cadastro de Pessoa</h1>

<form action="<%= request.getContextPath() %>/rest/pessoas" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" name="id" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Nome</td>
				<td><input type="text" name="nome" /></td>
			</tr>
			<tr>
				<td>Telefone</td>
				<td><input type="text" name="telefone" /></td>
			</tr>
			<tr>
				<td>Data de Nascimento</td>
				<td><input type="text" name="nascimento" /></td>
			</tr>
			<tr>
				<td>Foto</td>
				<td><input type="file" name="foto" value="" /></td>
			</tr>

			<tr colspan="2">
				<td><input type="submit" value="Gravar"></td>
			</tr>
		</table>
	</form>
</body>
</html>