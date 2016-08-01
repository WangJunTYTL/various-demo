<?php
/**
 * Created by IntelliJ IDEA.
 * User: wangjun
 * Date: 16/7/29
 * Time: 下午2:22
 */
require 'User2.php';
$user = new User2();
$user->setName('wj');
$user->setId(1);
echo json_encode($user);

