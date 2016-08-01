<?php

/**
 * Created by IntelliJ IDEA.
 * User: wangjun
 * Date: 16/7/28
 * Time: 下午2:07
 */
class User
{

    public $id;
    public $name;
    private $age;

    public function __construct($id, $name, $age)
    {
        $this->id = $id;
        $this->name = $name;
        $this->age = $age;
    }


}

$user = new User(1, 'wj', '26');
echo json_encode($user);