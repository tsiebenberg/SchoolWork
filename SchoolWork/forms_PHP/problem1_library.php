<?php

function stringCat($s1, $s2) {
  return $s1 . $s2;
}

/* setErrorLevel */
function setErrorLevel() {
	error_reporting(E_ERROR | E_WARNING | E_NOTICE | E_PARSE);
}

/* myStringCleanup */
function myStringCleanup($s1) {
	trim($s1);
	$s1 = str_replace(' ', '', $s1);
	$s1 = strtolower($s1);
	return $s1;
}

/* oddEven */
function oddEven($num) {
	if( $num % 2 == 0 )
		return "even";
	else
		return "odd";
}

/* buildArray */
function buildArray($a1, $a2, $a3, $a4) {
	$A = array($a1, $a2, $a3, $a4);
	return $A;
}

/* readLocalFile */
function readLocalFile() {
	$fileName = "external_file.txt";
	if( file_exists($fileName)) {
		$file = fopen($fileName, 'r');
		while( !feof($file)) {
			$str = $str . fgets($file) . "<br>";
		}
		fclose($file);
	}
	return $str;
}


/* serverSoftware */
function serverSoftware() {
	return $_SERVER['SERVER_SOFTWARE'];
}

/* makeObject */
function makeObject($str) {
	return $str;
}

class mySimpleClass {
  /* $secret property */
	
  public function __construct($s) {
    /* Store a copy of the incoming secret */
	$this->secret = $s;
  }
  
  public function getSecret() {
    /* Return a base 64 encoded version of the stored secret */
  }
}

/* getDatatype */
function getDatatype($arg) {
	return gettype($arg);
}
