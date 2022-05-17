<?php

namespace App\Http\Controllers;

use App\Models\Task;
use Illuminate\Http\Request;

class TaskController extends Controller {

    public static function destroy($id) {
        $validator = \Validator::make(['id' => $id], [
            'id' => 'required|int',
        ]);

        if ($validator->fails()) {
            return redirect('/')
                ->withInput()
                ->withErrors($validator);
        }

        $task = Task::findOrFail($id);
        try {
            $task->delete();
        } catch (\Exception $e) {
            return redirect('/')->withErrors($e->getMessage());
        }

        return redirect("/list/$task->list_id");
    }

    public static function store(Request $request) {
        $validator = \Validator::make($request->all(), [
            'name'    => 'required|string|max:255',
            'list_id' => 'required|int',
        ]);

        $list_id = $request->list_id ?? 0;
        if ($validator->fails()) {
            return redirect($list_id ? "/list/$list_id" : "/")
                ->withInput()
                ->withErrors($validator);
        }

        Task::create(['name' => $request->name, 'list_id' => $list_id]);
        return self::redirectToList($list_id);
    }

    public static function update(Request $request, $id) {
        $validator = \Validator::make($request->all() + ['id' => $id], [
            'done' => 'required|bool',
            'id'   => 'required|int',
        ]);

        if ($validator->fails()) {
            return redirect("/")
                ->withInput()
                ->withErrors($validator);
        }
        $task = Task::findOrFail($id);

        $task->done = $request->done;
        if (!$task->save()) {
            return self::redirectToList($task->list_id)->withErrors("Failed to update task");
        }

        return self::redirectToList($task->list_id);
    }
}
