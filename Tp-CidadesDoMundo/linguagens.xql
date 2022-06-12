xquery version "1.0";


declare namespace xsd = "http://www.w3.org/2001/XMLSchema";
declare function local:LinguaPedida($lingua as xsd:string) as element()*
{
for $x in doc("cidades.xml")/catalogo/cidade
where $x/linguagens/lingua = $lingua
return $x
};
<html><body>
<h1>Cidades que falam: {doc("pedido.xml")/pedido}</h1>
<ul>
{
let $v := doc("pedido.xml")/pedido
let $cidades := local:LinguaPedida($v)
return if (empty($cidades)) then
			<tr>Pesquisa sem resultados</tr>
else
	for $x in $cidades
	return
		<li>{$x/nome/text()}</li>
}

</ul></body>
</html>
