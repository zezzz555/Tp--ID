xquery version "1.0";

<queryResult>
{
for $x in doc("cidades.xml")/catalogo/cidade
let $v := doc("pedido.xml")/pedido
where $x/pais[.= $v] 
order by $x/nome
return <cidade>
			<nome>{$x/nome/text()}</nome>
			<codigopostal>{$x/codigopostal/text()}</codigopostal>
			<website>{$x/website/text()}</website>	
</cidade>
}
</queryResult>

