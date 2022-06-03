<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>

	<xsl:template match="catalogo">
	<html>
		<body>
	
	<h1>Lista de Bandeiras dos Países</h1>
	
	<table border="1">
	
		<tr> <th>Bandeira</th><th>Pais</th> </tr>
	
		<xsl:apply-templates>
	
			<xsl:sort select="pais"/>
	
		</xsl:apply-templates>
	
	</table>
		</body>
	</html>
	</xsl:template>
	<xsl:template match="cidade">
		<tr>
				<td><img src="{bandeirapais[not(. = preceding::bandeirapais)]}" width="100"/></td>
				<td><xsl:value-of select="pais[not(. = preceding::pais)]"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>


