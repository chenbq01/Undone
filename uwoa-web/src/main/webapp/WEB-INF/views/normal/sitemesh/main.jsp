<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><sitemesh:write property='title' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/normal/css/humanity/jquery-ui-1.10.3.custom.css"/>" />
<script src="<c:url value="/resources/normal/js/jquery-1.9.1.js"/>"></script>
<script src="<c:url value="/resources/normal/js/jquery-ui-1.10.3.custom.js"/>"></script>
<script src="<c:url value="/resources/normal/js/jquery.ui.datepicker-zh-TW.js"/>"></script>
<script type="text/javascript" src="http://jquery-json.googlecode.com/files/jquery.json-2.2.min.js"></script>
<script src="<c:url value="/resources/normal/js/formValidator-4.0.1.js"/>" type="text/javascript" charset="UTF-8"></script>
<script src="<c:url value="/resources/normal/js/formValidatorRegex.js"/>" type="text/javascript" charset="UTF-8"></script>
<script src="<c:url value="/resources/normal/js/My97DatePicker/WdatePicker.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/normal/js/jquery.blockUI.js"/>" type="text/javascript" ></script>
<script src="<c:url value="/resources/normal/js/jquery.PrintArea.js"/>" type="text/javascript" ></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/normal/css/global.css" />


  <style>

    body { font-size: 62.5%;  font-family: 宋体; font-size: 12px;}
	div {font-size: 12px;}

    input.text { margin-bottom:12px; width:95%; padding: .4em; }

    fieldset { padding:0; border:0; margin-top:25px; }

    h1 { font-size: 1.2em; margin: .6em 0; }

	.div_but {
		width:60px;
		height:25px;
		float:lift;
	}
	
	.ui-widget-content2 {
		border: 1px solid #e0cfc2;
		background: #ffffff 50% bottom repeat-x;
		color: #1e1b1d;
	}
	
    div#edit_div table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
 	div#edit_div table td.label { width:20%; text-align: right;}
 	div#edit_div table td.input { width:30%; text-align: left;}
 	div#edit_div table input { width:150px;}
 	div#edit_div table select { width:154px;}
 	div#edit_div table textarea { width:486px;height:60px;}
 	
 	div#detail_div table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
 	div#detail_div table td.label { width:20%; text-align: right;}
 	div#detail_div table td.input { width:30%; text-align: left;}
 	div#detail_div table input { width:150px;}
 	div#detail_div table select { width:154px;}
 	div#detail_div table textarea { width:486px;height:60px;}
 	
    div#div_search { width: 100%; margin: 0 0; }
    div#div_search table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
    div#div_search table th { border: 1px solid #eee; padding: .6em 10px; text-align: left;  font-size: 12px;}
 	div#div_search table td { border: 0px solid #eee; padding: .6em 5px; text-align: left;  font-size: 12px;}
 	div#div_search table td.label { width:11%; text-align: right;}
 	div#div_search table td.input { width:20%; text-align: left;}
 	div#div_search table td.button { width:7%; text-align: center;}
 	div#div_search table input { width:130px;}
 	div#div_search table select { width:134px;}
 	
 	div#data_table { width: 100%; margin: 0 0; }
    div#data_table table { margin: 0.2em 0; border-collapse: collapse; width: 100%; }
    div#data_table table th { border: 1px solid #eee; padding: .6em 10px; text-align: center;  font-size: 12px;}
 	div#data_table table td { border: 1px solid #eee; padding: .6em 5px; text-align: left;  font-size: 12px;}
 	div#data_table table td#l { text-align: left;}
 	div#data_table table td#c { text-align: center;}
 	div#data_table table td#r { text-align: right;}
 	
 	div#page_info { width: 100%; margin: 0 0; text-align: right;}

    div#toolbar_table { width: 100%; text-align:right; margin: 0 0;  font-size: 12px;}
    
    .ui-dialog .ui-state-error { padding: .3em; }

    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>


	<script>
	$(function() {
		
		$( "#accordion" ).accordion();
		

		
		var availableTags = [
			"ActionScript",
			"AppleScript",
			"Asp",
			"BASIC",
			"C",
			"C++",
			"Clojure",
			"COBOL",
			"ColdFusion",
			"Erlang",
			"Fortran",
			"Groovy",
			"Haskell",
			"Java",
			"JavaScript",
			"Lisp",
			"Perl",
			"PHP",
			"Python",
			"Ruby",
			"Scala",
			"Scheme"
		];
		$( "#autocomplete" ).autocomplete({
			source: availableTags
		});
		

		
		$( "#button" ).button();
		$( "#radioset" ).buttonset();
		

		
		$( "#tabs" ).tabs();
		

		
		$( "#dialog" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "Ok",
					click: function() {
						$( this ).dialog( "close" );
					}
				},
				{
					text: "Cancel",
					click: function() {
						$( this ).dialog( "close" );
					}
				}
			]
		});

		// Link to open the dialog
		$( "#dialog-link" ).click(function( event ) {
			$( "#dialog" ).dialog( "open" );
			event.preventDefault();
		});
		

		
		$( "#datepicker" ).datepicker({
			inline: true
		});
		

		
		$( "#slider" ).slider({
			range: true,
			values: [ 17, 67 ]
		});
		

		
		$( "#progressbar" ).progressbar({
			value: 20
		});
		

		// Hover states on the static widgets
		$( "#dialog-link, #icons li" ).hover(
			function() {
				$( this ).addClass( "ui-state-hover" );
			},
			function() {
				$( this ).removeClass( "ui-state-hover" );
			}
		);
	});
	
	  (function( $ ) {
		    $.widget( "custom.combobox", {
		      _create: function() {
		        this.wrapper = $( "<span>" )
		          .addClass( "custom-combobox" )
		          .insertAfter( this.element );
		 
		        this.element.hide();
		        this._createAutocomplete();
		      },
		 
		      _createAutocomplete: function() {
		        var selected = this.element.children( ":selected" ),
		          value = selected.val() ? selected.text() : "";
		 
		        this.input = $( "<input>" )
		          .appendTo( this.wrapper )
		          .val( value )
		          .attr( "title", "" )
		          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default" )
		          .autocomplete({
		            delay: 0,
		            minLength: 0,
		            source: $.proxy( this, "_source" )
		          })
		          .tooltip({
		            tooltipClass: "ui-state-highlight"
		          });
		 
		        this._on( this.input, {
		          autocompleteselect: function( event, ui ) {
		            ui.item.option.selected = true;
		            this._trigger( "select", event, {
		              item: ui.item.option
		            });
		            comboboxCallback(ui.item.option);
		          },
		 
		          autocompletechange: "_removeIfInvalid"
		        });
		      },
		 
		 
		      _source: function( request, response ) {
		        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
		        response( this.element.children( "option" ).map(function() {
		          var text = $( this ).text();
		          if ( this.value && ( !request.term || matcher.test(text) ) )
		            return {
		              label: text,
		              value: text,
		              option: this
		            };
		        }) );
		      },
		 
		      _removeIfInvalid: function( event, ui ) {
		 
		        // Selected an item, nothing to do
		        if ( ui.item ) {
		          return;
		        }
		 
		        // Search for a match (case-insensitive)
		        var value = this.input.val(),
		          valueLowerCase = value.toLowerCase(),
		          valid = false;
		        this.element.children( "option" ).each(function() {
		          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
		            this.selected = valid = true;
		            return false;
		          }
		        });
		 
		        // Found a match, nothing to do
		        if ( valid ) {
		          return;
		        }
		 
		        // Remove invalid value
		        this.input
		          .val( "" )
		          .attr( "title", value + " 没有找到" )
		          .tooltip( "open" );
		        this.element.val( "" );
		        this._delay(function() {
		          this.input.tooltip( "close" ).attr( "title", "" );
		        }, 2500 );
		        this.input.data( "ui-autocomplete" ).term = "";
		      },
		 
		      _destroy: function() {
		        this.wrapper.remove();
		        this.element.show();
		      }
		    });
		  })( jQuery );
	</script>
	<style>
	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		margin: 5px;
	}
	.demoHeaders {
		margin-top: 2em;
	}
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
	</style>
<script>
/**
 * 禁用页面backspace后退
 */
function backspace(evt){
	evt=evt?evt:window.event;
	if (evt.keyCode == 8 && evt.srcElement.tagName != "INPUT" && evt.srcElement.type != "text" && evt.srcElement.tagName != "TEXTAREA"){
		evt.returnValue=false;
	}
}
</script>
	
</head>

<body onkeydown="backspace(event)">
	<%@ include file="./../base/confirmDialog.jsp"%>
	<%@ include file="./../base/alertDialog.jsp"%>
    <%@ include file="./../base/alertWait.jsp"%>
    <%@ include file="./../base/uploadDialog.jsp"%>
	<sitemesh:write property='body'/>
</body>
</html>