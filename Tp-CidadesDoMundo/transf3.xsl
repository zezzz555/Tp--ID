<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes"/>
	
<xsl:template match="catalogo">
		<cidade>
	
			<xsl:for-each select="cidade">
				<xsl:sort select="number(nhabitantes)" data-type="number" order="descending"/>
					<xsl:if test="nhabitantes[. &gt; preceding::nhabitantes]">
	
						<xsl:if test="count(preceding::nhabitantes) &lt; 7">
	
							<nome>					
								<xsl:value-of select="nome"/>
							</nome>
						</xsl:if>
					</xsl:if>
			</xsl:for-each>
		</cidade>
	</xsl:template>

</xsl:stylesheet>


