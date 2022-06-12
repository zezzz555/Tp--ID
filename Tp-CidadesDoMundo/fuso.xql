xquery version "1.0";

<resultados>
{
for $x in doc("cidades.xml")/catalogo/cidade
let $v := doc("pedido.xml")/pedido
where contains($x/fuso,$v)
order by $x/nome
return ("&#10;","cidade: ", $x/nome/text(),"----",$x/pais/text())
}
</resultados>
