<?php
/**
 * php 中异常分为两种 一种是Error 一种是Exception
 * User: wangjun
 * Date: 16/7/28
 * Time: 上午11:08
 */
$x = 1;
$y = 0;
$c = $x / $y;

// 这里会向下继续执行
echo "$x/$y=" . $c;


//create function with an exception
function checkNum($number)
{
    if ($number > 1) {
        throw new Exception("Value must be 1 or below");
    }
    return true;
}

//trigger exception
checkNum(2);

// 不会向下继续执行
echo 'hello';