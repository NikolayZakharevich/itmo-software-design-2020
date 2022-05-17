<?php

namespace App\Http\Controllers;

use App\Models\TaskList;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    public static function redirectToLastList() {
        $list = TaskList::latest()->firstOrFail();
        return redirect("/list/$list->id");
    }

    public static function redirectToList(int $list_id) {
        $list = TaskList::findOrFail($list_id);
        return redirect("/list/$list->id");
    }
}
