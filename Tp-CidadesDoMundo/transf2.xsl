<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="3.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" />
	
	<xsl:template match="catalogo">
		
<xsl:text>Lista de cidades:&#xa;</xsl:text>
		<xsl:apply-templates select="cidade">
			<xsl:sort select="nome"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="cidade">
			<xsl:if test="pais='Espanha'">
				<xsl:text>&#x9;</xsl:text>
					<xsl:value-of select="nome"/>
				<xsl:text>&#xa;</xsl:text>
			</xsl:if>
	</xsl:template>

</xsl:stylesheet>
