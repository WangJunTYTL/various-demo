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
echo json_encode($user) . "\n";

echo '-----------------php reflection-------------------' . "\n";
$reflect = new ReflectionClass("User2");
echo "class name:" . $reflect->name . "\n";
echo "file name:" . $reflect->getFileName() . "\n";


interface User3
{
    /**
     * @param string $name
     * @param int $age
     * @return User2
     */
    function getName($name = 'hello', $age = 1);
}


$reflect = new ReflectionClass('User3');
$methods = $reflect->getMethods();
foreach ($methods as $method) {
    echo "method:" . $method->getName() . "\n";
    $params = $method->getParameters();
    foreach ($params as $arg) {
        echo "---" . $arg->getName() . "\n";;
        if ($arg->isDefaultValueAvailable()) {
            echo "------" . $arg->getDefaultValue() . "\n";
        }
    }
    $api = new ApiProxy();
    $api->doInvoke();
}

class ApiProxy
{

    function doInvoke($clazz)
    {
        $reflect = new ReflectionClass($clazz);
        $methods = $reflect->getMethods();
        foreach ($methods as $method) {
            echo "method:" . $method->getName() . "\n";
            $params = $method->getParameters();
            foreach ($params as $arg) {
                echo "---" . $arg->getName() . "\n";;
                if ($arg->isDefaultValueAvailable()) {
                    echo "------" . $arg->getDefaultValue() . "\n";
                }
            }
        }
        $user = new User2();
        $user->setName('wj');
        $user->setId(1);
        return json_encode(json_encode($user));
    }
}

