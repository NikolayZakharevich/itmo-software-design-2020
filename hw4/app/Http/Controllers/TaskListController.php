<?php

namespace App\Http\Controllers;

use App\Models\TaskList;
use Illuminate\Http\Request;

class TaskListController extends Controller {

    public static function index() {
        return self::redirectToLastList();
    }

    public static function show(Request $request, $id) {
        $validator = \Validator::make(['id' => $id], [
            'id' => 'required|int',
        ]);
        if ($validator->fails()) {
            return redirect('/')
                ->withInput()
                ->withErrors($validator);
        }

        $list = TaskList::findOrFail($id);
        return view('tasks', [
            'tasks'   => $list->tasks,
            'list_id' => $id,
            'name' => $list->name
        ]);
    }

    public static function store() {
        $list = TaskList::create(['name' => '']);
        $list->name = "TaskList #$list->id";
        $list->save();
        return self::redirectToList($list->id);
    }

    public static function destroy($id) {
        $validator = \Validator::make(['id' => $id], [
            'id' => 'required|int',
        ]);

        if ($validator->fails()) {
            return self::redirectToLastList()
                ->withInput()
                ->withErrors($validator);
        }

        $task = TaskList::findOrFail($id);
        try {
            $task->delete();
        } catch (\Exception $e) {
            return self::redirectToLastList()->withErrors($e->getMessage());
        }

        return self::redirectToLastList();
    }
}
