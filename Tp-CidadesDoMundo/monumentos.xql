xquery version "1.0";

declare namespace xsd = "http://www.w3.org/2001/XMLSchema";
declare function local:CidadePedida($cidade as xsd:string) as element()*
{
for $x in doc("cidades.xml")/catalogo/cidade
where $x/nome = $cidade
return $x
};

<html><body>
<h1>Imagens de monumentos/landmarks de {doc("pedido.xml")/pedido} </h1>
<table border="1">
<tr><th>Foto</th></tr>
{
let $v := doc("pedido.xml")/pedido
let $monumentos := local:CidadePedida($v)/monumentos
return if (empty($monumentos)) then
			<tr>Pesquisa sem resultados</tr>
else
	for $x in $monumentos/monumento
	return
		<tr>
			<td><img src="{$x/text()}" width="150"/></td>
		</tr>
}
</table></body>
</html>

